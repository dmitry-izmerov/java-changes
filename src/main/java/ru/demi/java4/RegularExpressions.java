package ru.demi.java4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressions {
    public static void main(String[] args) {
        Pattern postCodePattern = Pattern.compile("\\d{6}");
        Matcher matcher = postCodePattern.matcher("Use the code: 111222 for sending a mail to me.");
        while (matcher.find()) {
            System.out.println("Matched code: " + matcher.group());
        }
    }
}
