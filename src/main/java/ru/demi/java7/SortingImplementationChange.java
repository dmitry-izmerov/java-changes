package ru.demi.java7;

import java.util.Arrays;

public class SortingImplementationChange {

    public static void main(String[] args) {
        int[] primitives = {2, 1, 4};
        String[] objects = {"banana", "apple", "orange"};

        // uses Dual-Pivot Quicksort
        Arrays.sort(primitives);
        // uses Tim sort
        Arrays.sort(objects);
    }
}
