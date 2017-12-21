package com.barbarum.tutorial.code.string;

public class ReverseWords {

    public static String reverse(String sentence) {
        if (sentence == null || sentence.length() <= 1) {
            return sentence;
        }

        char chars[] = sentence.toCharArray();

        reverse(chars, 0, sentence.length() - 1);

        for (int start = 0, end = 0; end <= chars.length; end++) {
            if (end == chars.length || chars[end] == ' ') {
                reverse(chars, start, end - 1);
                start = end + 1;
            }
        }

        return new String(chars);
    }

    private static void reverse(char[] sentence, int start, int end) {
        if (start >= sentence.length) {
            return;
        }

        while (start < end) {
            char c = sentence[start];
            sentence[start] = sentence[end];
            sentence[end] = c;
            start++;
            end--;
        }
    }

    public static void main(String args[]) {
        System.out.println(reverse("This is a string"));

        System.out.println(String.format("'%s'", "a".substring(1, 1)));
    }
}
