package com.barbarum.tutorial.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 1) {
            return 1;
        }

        int[] lengths = new int[s.length()];
        Set<Character> set;

        for (int i = 0; i < lengths.length; i++) {
            set = new HashSet<>();
            for (int j = i; j < lengths.length && !set.contains(s.charAt(j)); j++) {
                set.add(s.charAt(j));
            }
            lengths[i] = set.size();
        }

        int max = 0;

        for (int length : lengths) {
            max = length > max ? length : max;
        }

        return max;
    }

    public static int lengthByHashMap(String s) {

        Map<Character, Integer> map = new HashMap<>();

        int start = 0;
        int longestLength = 0;

        for (int i = 0; i < s.length(); i++) {
            Character currentChar = s.charAt(i);
            Integer lastMatchedIndex = map.get(currentChar);

            if (lastMatchedIndex == null || lastMatchedIndex < start) {
                map.put(currentChar, i);
            } else {
                longestLength = i - start > longestLength ? i - start : longestLength;
                start = lastMatchedIndex + 1;
                map.put(currentChar, i);

                if (longestLength >= s.length() - start) {
                    return longestLength;
                }
            }
        }

        int lastLength = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= start) {
                lastLength++;
            }
        }

        return Math.max(longestLength, lastLength);
    }

    public static int lengthByArrayAndASCII(String s) {

        int[] array = new int[128 - 32];

        for (int i = 0; i < array.length; i++) {
            array[i] = -1;
        }

        int max = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            int currentChar = s.charAt(i);
            start = Math.max(start, array[currentChar - 32] + 1);
            array[currentChar - 32] = i;
            max = Math.max(max, i - start + 1);
        }
        return max;
    }

    public static int lengthByMaximumRange(String s) {
        if (s == null || s.isEmpty()) return 0;
        if (s.length() == 1) return 1;

        int[] array = new int[128];

        int max = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            int currentChar = s.charAt(i);
            // Get the start pointer
            start = Math.max(start, array[currentChar]);
            // calculate max length for current char
            max = Math.max(max, i - start + 1);
            // Store the start pointer for matched char next time.
            array[currentChar] = i + 1;
        }
        return max;
    }
}
