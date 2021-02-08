package ru.demi.java7.exception;

public class RethrowExceptionsWithMoreTypesExample {

    // Before Java 7:
    // public static void main(String[] args) throws Exception {
    // After Java 7:
    public static void main(String[] args) throws OneException, AnotherException {
        try {
            throwsOneException();
            throwsAnotherException();
        } catch (Exception e) {
           throw e;
        }
    }

    static void throwsOneException() throws OneException {}
    static void throwsAnotherException() throws AnotherException {}

    static class OneException extends Exception {}
    static class AnotherException extends Exception {}
}
