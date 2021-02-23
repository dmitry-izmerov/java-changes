package ru.demi.java10;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LocalVarTypeInferenceExample {

    public static void main(String[] args) {
        var list = new ArrayList<String>();
        var size = list.size();
        var nums = list.stream().map(Integer::parseInt).collect(Collectors.toList());

        for (var item : list) {
            item.length();
        }

        for (var i = 0; i < list.size(); ++i) {
            list.get(i);
        }

        // The identifier var is not a keyword; instead it is a reserved type name.
        // This means that code that uses var as a variable, method, or package name will not be affected;
        // code that uses var as a class or interface name will be affected
        var var = 5;
    }
}
