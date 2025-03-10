package com.codegym.casestudy_module4.ulti;

public class StringUtil {
    public enum PadType { LEFT, RIGHT }

    public static String strPad(String input, int length, char padChar, PadType padType) {
        if (input == null) input = "";
        if (input.length() >= length) return input;

        StringBuilder sb = new StringBuilder();
        int padLength = length - input.length();

        if (padType == PadType.LEFT) {
            for (int i = 0; i < padLength; i++) {
                sb.append(padChar);
            }
            sb.append(input);
        } else {
            sb.append(input);
            for (int i = 0; i < padLength; i++) {
                sb.append(padChar);
            }
        }
        return sb.toString();
    }
}
