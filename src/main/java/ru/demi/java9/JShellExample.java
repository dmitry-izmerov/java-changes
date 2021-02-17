package ru.demi.java9;

import java.io.IOException;

public class JShellExample {

    public static void main(String[] args) throws IOException, InterruptedException {
        var pb = new ProcessBuilder("jshell", "--version").inheritIO();
        var process = pb.start();
        process.waitFor();
    }
}
