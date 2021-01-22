package ru.demi.java4.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class NioChatServer implements AutoCloseable {

    private static final Logger log = LoggerFactory.getLogger(NioChatServer.class);

    private static final int DEFAULT_SIZE_IN_BYTES = 1024;
    private static final String ERROR_MSG = "You should pass at least 2 arguments for startup of a server: 1 - hostname, 2 - port";

    private final ByteBuffer buffer;
    private final ServerSocketChannel socketChannel;
    private boolean isReading = true;
    private SelectionKey lastReadKey = null;
    private String message;

    public static void main(String[] arguments) {
        var args = ArgsUtil.handle(arguments, DEFAULT_SIZE_IN_BYTES, ERROR_MSG);
        try (var chatServer = new NioChatServer(args.address(), args.bufferSize())) {
            chatServer.startProcessing();
        }
    }

    public NioChatServer(InetSocketAddress socketAddress, int bufferSize) {
        try {
            socketChannel = ServerSocketChannel.open();
            socketChannel.socket().bind(socketAddress);
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        buffer = ByteBuffer.allocate(bufferSize);

        log.info("Server started at port: {}", socketAddress.getPort());
    }

    public void startProcessing() {
        try {
            var selector = Selector.open();
            socketChannel.register(selector, socketChannel.validOps());

            while (true) {
                selector.select();
                var selectedKeys = selector.selectedKeys();
                for (var iterator = selectedKeys.iterator(); iterator.hasNext();) {
                    var key = iterator.next();
                    iterator.remove();

                    if (key.isAcceptable()) {
                        acceptClient(selector);
                    } else if (key.isReadable() && isReading) {
                        message = processRead(key);
                        lastReadKey = key;
                    } else if (key.isWritable() && !isReading && !key.equals(lastReadKey)) {
                        sendMessage(message, key);
                    }
                }
                if (!isReading) {
                    message = null;
                }
                isReading = !isReading;
            }
        } catch (Exception e) {
            log.error("Main processing loop failed", e);
        }
    }

    private void acceptClient(Selector selector) throws IOException {
        var clientChannel = socketChannel.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, clientChannel.validOps());
        log.info("Connection is accepted from: {}", clientChannel.getRemoteAddress());
    }

    private String processRead(SelectionKey key) throws IOException {
        var clientChannel = (SocketChannel) key.channel();
        buffer.clear();
        var read = clientChannel.read(buffer);
        if (read == -1) {
            var remoteAddress = clientChannel.getRemoteAddress();
            clientChannel.close();
            key.cancel();
            log.info("Client is disconnected from: {}", remoteAddress);
            return null;
        }
        buffer.flip();
        var bytes = new byte[read];
        buffer.get(bytes);
        var msg = new String(bytes, UTF_8).trim();
        buffer.clear();
        if (!msg.isBlank()) {
            log.info("Message is read: {}", msg);
        }
        return msg;
    }

    private void sendMessage(String message, SelectionKey key) {
        if (message == null || message.isBlank() || !key.isValid() || !key.isWritable()) {
            return;
        }
        buffer.clear();
        buffer.put(message.getBytes(UTF_8));
        buffer.flip();
        var clientChannel = (SocketChannel) key.channel();
        try {
            clientChannel.write(buffer);
            log.info("Message is sent for selection key: {}", key);
        } catch (IOException e) {
            log.error("Sending message is failed.", e);
        }
        buffer.clear();
        log.info("Message is sent to all listeners");
    }

    @Override
    public void close() {
        try {
            socketChannel.close();
        } catch (IOException e) {
            log.error("Error happened while closing server socket channel", e);
        }
    }

    static class ArgsUtil {
        static Args handle(String[] args, int defaultBufSize, String errorMsg) {
            if (args.length < 2) {
                throw new IllegalArgumentException(errorMsg);
            }
            var address = new InetSocketAddress(args[0], Integer.parseInt(args[1]));
            int bufferSize = defaultBufSize;
            var messages = Collections.<String>emptyList();
            if (args.length > 2) {
                var nextArg = 3;
                try {
                    bufferSize = args.length == 3 ? Integer.parseInt(args[2]) : defaultBufSize;
                } catch (NumberFormatException e) {
                    bufferSize = defaultBufSize;
                    nextArg = 2;
                }

                if (args.length == nextArg + 1) {
                    messages = Arrays.asList(args[nextArg].split(";"));
                }
            }

            return new Args(address, bufferSize, messages);
        }
    }

    static record Args(InetSocketAddress address, int bufferSize, List<String> messages) {}
}
