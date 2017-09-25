package com.barbarum.tutorial.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/zigzag-conversion/description/

 The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

 P   A   H   N
 A P L S I I G
 Y   I   R
 And then read line by line: "PAHNAPLSIIGYIR"
 Write the code that will take a string and make this conversion given a number of rows:

 string convert(string text, int nRows);
 convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

 */
public class ZigZagConversion {

    public static String convert(String s, int numRows) {
        if (numRows <= 0 || s == null) {
            throw new IllegalArgumentException();
        }
        if (s.length() == 0 || numRows == 1) {
            return s;
        }

        ArrayList<Character>[] matrix = new ArrayList[numRows];

        int rowPoint = 0;
        boolean isDown = true;

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new ArrayList<>();
        }

        for (Character c : s.toCharArray()) {
            matrix[isDown ? rowPoint++ : rowPoint--].add(c);
            if (isDown && rowPoint == numRows - 1) {
                isDown = false;
            }
            if (!isDown && rowPoint == 0) {
                isDown = true;
            }
        }

        StringBuilder builder = new StringBuilder();
        Arrays.stream(matrix).forEach(row -> row.forEach(builder::append));
        return builder.toString();
    }


    public static String convertWithSB(String s, int numRows) {
        if (numRows <= 0 || s == null) {
            throw new IllegalArgumentException();
        }
        if (s.length() == 0 || numRows == 1) {
            return s;
        }

        StringBuilder[] matrix = new StringBuilder[numRows];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new StringBuilder();
        }

        int rowPoint = 0;
        boolean isDown = true;

        for (Character c : s.toCharArray()) {
            matrix[isDown ? rowPoint++ : rowPoint--].append(c);
            if (isDown && rowPoint == numRows - 1) {
                isDown = false;
            }
            if (!isDown && rowPoint == 0) {
                isDown = true;
            }
        }

        StringBuilder builder = new StringBuilder();
        Arrays.stream(matrix).forEach(builder::append);

        return builder.toString();
    }
}
