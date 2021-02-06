package ru.demi.java7;

public class StringsInSwitchExample {

    public static void main(String[] args) {
        var operation = args[0];

        var first = 10;
        var second = 2;
        var result = 0;
        switch (operation) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "*":
                result = first * second;
                break;
            default:
                throw new UnsupportedOperationException("Supported operations: +-*");
        }
        System.out.printf("result: %d", result);
    }
}
