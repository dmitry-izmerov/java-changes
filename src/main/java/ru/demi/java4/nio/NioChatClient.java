package ru.demi.java4.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.nio.charset.StandardCharsets.UTF_8;

public class NioChatClient implements AutoCloseable {

    private static final Logger log = LoggerFactory.getLogger(NioChatClient.class);

    private static final int DEFAULT_SIZE_IN_BYTES = 1024;
    private static final int INITIAL_DELAY_IN_SEC = 2;
    private static final int DELAY_BETWEEN_SENDING_IN_SEC = 4;
    private static final String ERROR_MSG = "You should pass at least 2 arguments for connection to a server: 1 - hostname, 2 - port";

    private final SocketChannel socketChannel;
    private final ByteBuffer buffer;

    public static void main(String[] arguments) {
        var args = NioChatServer.ArgsUtil.handle(arguments, DEFAULT_SIZE_IN_BYTES, ERROR_MSG);
        try (var chatClient = new NioChatClient(args.address(), args.bufferSize())) {
            chatClient.sendMessagesAndListen(args.messages());
        }
    }

    public NioChatClient(InetSocketAddress socketAddress, int bufferSize) {
        try {
            socketChannel = SocketChannel.open(socketAddress);
            log.info("Connected to server at: {}", socketAddress);
            log.info("Started at: {}", socketChannel.getLocalAddress());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        buffer = ByteBuffer.allocate(bufferSize);
    }

    public void sendMessagesAndListen(List<String> messages) {
        if (!socketChannel.isConnected()) {
            throw new RuntimeException("Client is not connected.");
        }

        try {
            socketChannel.configureBlocking(false);
            TimeUnit.SECONDS.sleep(INITIAL_DELAY_IN_SEC);

            var selector = Selector.open();
            socketChannel.register(selector, socketChannel.validOps());
            sendMessages(messages);
            listenResponse(selector);
        } catch (Exception e) {
            log.error("Error when sending messages and listen", e);
            this.close();
        }
    }

    private void sendMessages(List<String> messages) {
        if (messages == null || messages.isEmpty()) {
            return;
        }

        new Thread(() -> {
            log.info("Thread for sending messages started");
            messages.forEach(m -> {
                var message = m.getBytes(UTF_8);
                var buffer = ByteBuffer.wrap(message);
                try {
                    socketChannel.write(buffer);
                    buffer.clear();
                } catch (IOException e) {
                    log.error("Writing in channel error", e);
                }
                log.info("Sending message: {}", m);
                try {
                    TimeUnit.SECONDS.sleep(DELAY_BETWEEN_SENDING_IN_SEC);
                } catch (InterruptedException e) {
                    log.error("Thread was interrupted", e);
                }
            });
        }).start();
    }

    private void listenResponse(Selector selector) throws IOException {
        log.info("Loop for reading messages started");
        while (true) {
            selector.selectNow();
            for (var iterator = selector.selectedKeys().iterator(); iterator.hasNext(); ) {
                var key = iterator.next();
                iterator.remove();
                if (key.isReadable()) {
                    var channel = (SocketChannel) key.channel();
                    buffer.clear();
                    var read = channel.read(buffer);
                    if (read == -1) {
                        continue;
                    }
                    buffer.flip();
                    var bytes = new byte[read];
                    buffer.get(bytes);
                    var str = new String(bytes, UTF_8).trim();
                    log.info("Message is received: {}", str);
                    buffer.clear();
                }
            }
        }
    }

    @Override
    public void close() {
        try {
            socketChannel.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
