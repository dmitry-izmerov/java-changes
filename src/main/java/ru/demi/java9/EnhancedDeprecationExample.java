package ru.demi.java9;

public interface EnhancedDeprecationExample {

    void apiMethod();

    @Deprecated(since = "2.0.0", forRemoval = true)
    void deprecatedApiMethod();
}
