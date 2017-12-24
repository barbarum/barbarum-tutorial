package com.barbarum.tutorial.code.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchForPatterns {

    public static List<Integer> search(String s, String pattern) {
        if (s == null || s.length() == 0 || pattern == null || pattern.length() == 0) {
            return Collections.emptyList();
        }

        int cache[] = new int[pattern.length()];
        calculateRepeat(pattern, cache);

        List<Integer> result = new ArrayList<>();
        search(s, pattern, cache, result);

        return result;
    }

    private static void calculateRepeat(String pattern, int[] cache) {
        int length = 0;
        cache[0] = 0;

        for (int i = 1; i < pattern.length(); ) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                cache[i] = length;
                i++;
            } else if (length != 0) {
                length = cache[length - 1];
            } else {
                cache[i] = 0;
                i++;
            }
        }
    }

    private static void search(String s, String pattern, int[] cache, List<Integer> result) {
        for (int i = 0, j = 0; i < s.length(); ) {
            if (s.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) {
                    result.add(i - j);
                    j = cache[j - 1];
                }
                continue;
            }

            if (j != 0) {
                j = cache[j - 1];
            } else {
                i++;
            }
        }
    }

    public static void main(String args[]) {
        System.out.println(search("AABAACAADAABAABA", "AABA"));
    }
}
