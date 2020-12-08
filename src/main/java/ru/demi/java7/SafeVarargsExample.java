package ru.demi.java7;

import java.util.Arrays;

public class SafeVarargsExample {

    public static void main(String[] args) {
        Integer[] array = newArrayOf(1, 2, 3);
        System.out.println(Arrays.toString(array));
    }

    @SafeVarargs
    private static <E> E[] newArrayOf(E... elements) {
        return elements;
    }
}
