package ru.demi.java4;

public class AssertKeyWord {

    // run with -ea or -enableassertions option
    public static void main(String[] args) {
        assert args != null && args.length > 0 : "no arguments passed";
    }
}
