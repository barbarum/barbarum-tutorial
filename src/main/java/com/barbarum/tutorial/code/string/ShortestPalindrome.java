package com.barbarum.tutorial.code.string;

public class ShortestPalindrome {

    public static String findShortInsertToMakePalindrome(String s) {
        int length;
        if (s == null || (length = s.length()) <= 1) {
            return "";
        }

        int index = length - 1;

        for (; index >= 0; index--) {
            if (checkIfPalindrome(s, 0, index)) {
                break;
            }
        }

        return reverse(s, index + 1);
    }

    private static String reverse(String s, int start) {
        StringBuilder builder = new StringBuilder();
        for (int i = s.length() - 1; i >= start; i--) {
            builder.append(s.charAt(i));
        }
        return builder.toString();
    }

    private static boolean checkIfPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }


    public static void main(String args[]) {
        System.out.println(findShortInsertToMakePalindrome("aba"));
    }

}
