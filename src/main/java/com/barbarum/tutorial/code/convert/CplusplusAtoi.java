package com.barbarum.tutorial.code.convert;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/description/
 * Implement atoi to convert a string to an integer.
 * http://www.cplusplus.com/reference/cstdlib/atoi/
 */
public class CplusplusAtoi {

    public static int easyWay(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        long base = 0;
        int sign = 1;
        int index = 0;

        // Remove white space
        while (index < str.length() && str.charAt(index) == ' ') {
            index++;
        }
        // Discover sign
        if (index < str.length() && (str.charAt(index) == '-' || str.charAt(index) == '+')) {
            sign = str.charAt(index++) == '-' ? -1 : 1;
        }

        while (index < str.length() && isNum(str.charAt(index))) {
            base = base * 10 + charToInt(str.charAt(index++));
            if ((base * sign) >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if ((base * sign) <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        return (int) (base * sign);
    }

    public static int myAtoi(String str) {
        String temp = str;

        if (temp == null || (temp = temp.trim()).length() == 0) {
            return 0;
        }

        if (!isNum(temp.charAt(0))) {
            if (temp.length() == 1 || (temp.charAt(0) != '+' && temp.charAt(0) != '-')) {
                return 0;
            }
            if (!isNum(temp.charAt(1))) {
                return 0;
            }
        }

        long unsignedResult = 0;
        int index = 0;
        int signed = 1;

        do {
            if (index != 0 || isNum(temp.charAt(index))) {
                unsignedResult = unsignedResult * 10 + charToInt(temp.charAt(index));
            } else if (temp.charAt(index) == '-') {
                signed = -1;
            }

            if ((signed * unsignedResult) > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if ((signed * unsignedResult) < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            index++;
        } while (index < temp.length() && isNum(temp.charAt(index)));

        return (int) (signed * unsignedResult);
    }

    public static int charToInt(char c) {
        return c - '0';
    }

    public static boolean isNum(char c) {
        return charToInt(c) >= 0 && charToInt(c) <= 9;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }
}
