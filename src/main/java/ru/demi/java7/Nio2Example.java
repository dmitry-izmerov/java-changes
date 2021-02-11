package ru.demi.java7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardCopyOption.*;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardWatchEventKinds.*;

public class Nio2Example {

    private static final Logger log = LoggerFactory.getLogger(Nio2Example.class);

    // java.nio.file, java.nio.file.attribute were added in Java 7
    // here are some examples of new functionality
    public static void main(String[] args) throws InterruptedException {
        var file = writeFile();
        var dir = Path.of("./test-dir");
        var thread = registerWatcher(dir);
        TimeUnit.SECONDS.sleep(2);
        createDir(dir);
        moveFileInDir(file, dir);
        removeDir(dir);
        TimeUnit.SECONDS.sleep(2);
        thread.ifPresent(Thread::interrupt);
    }

    private static Optional<Thread> registerWatcher(Path file) {
        Optional<Thread> thread = Optional.empty();
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            var dir = file.getParent().toAbsolutePath();
            dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
             Thread t = new Thread(() -> {
                while (true) {
                    WatchKey key;
                    try {
                        key = watcher.take();
                    } catch (InterruptedException e) {
                        return;
                    }

                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();
                        if (kind == OVERFLOW) {
                            continue;
                        }

                        log.info("event happened: {}", event.kind());
                    }
                }
            });
            t.start();
            thread = Optional.of(t);
        } catch (IOException e) {
            log.error("Error happened", e);
        }
        return thread;
    }

    private static Path writeFile() {
        var path = Path.of("newfile");
        try (var file = Files.newBufferedWriter(path, StandardCharsets.UTF_8, CREATE)) {
            file.write("some content");
        } catch (IOException e) {
            log.error("Writing to file {} failed", path, e);
        }
        return path;
    }

    private static void createDir(Path dirPath) {
        var attrs = PosixFilePermissions.fromString("rwxrwxrwx");
        try {
            Files.createDirectory(dirPath, PosixFilePermissions.asFileAttribute(attrs));
        } catch (IOException e) {
            log.error("Error creating directory: {}", dirPath, e);
        }
    }

    private static void moveFileInDir(Path file, Path dir) {
        var fileName = file.getFileName();
        Path target = dir.resolve(fileName);
        try {
            Files.move(file, target, REPLACE_EXISTING, ATOMIC_MOVE);
        } catch (IOException e) {
            log.error("Problem with moving of file: {} from directory: {}", file, dir, e);
        }
    }

    private static void removeDir(Path dir) {
        try {
            Files.walkFileTree(dir, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }
            });
            Files.delete(dir);
        } catch (IOException e) {
            log.error("Cannot remove dir: {}", dir, e);
        }
    }
}
