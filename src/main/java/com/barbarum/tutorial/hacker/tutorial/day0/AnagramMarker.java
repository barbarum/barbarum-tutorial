package com.barbarum.tutorial.hacker.tutorial.day0;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class AnagramMarker {

    public static int numberNeeded(String first, String second) {
        if (first == null || first.isEmpty()) {
            return second == null || second.isEmpty() ? 0 : second.length();
        }
        if (second == null || second.isEmpty()) {
            return first.length();
        }

        int[] gap = new int[26];
        for (char item : first.toCharArray()) {
            gap[item - 'a']++;
        }
        for (char item : second.toCharArray()) {
            gap[item - 'a']--;
        }

        int result = 0;
        for (int item : gap) {
            result += Math.abs(item);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}
