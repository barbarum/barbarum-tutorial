package com.barbarum.tutorial.code.string;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-common-prefix/description/
 */
public class LongestCommonPrefix {

    public static String longestCommonPrefixWithString(String[] strs) {
        if (strs == null || strs.length == 0 || strs[0] == null || strs[0].isEmpty()) {
            return "";
        }

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            if (strs[i] == null || strs[i].isEmpty()) {
                return "";
            }
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if ("".equals(prefix)) {
                    return prefix;
                }
            }
        }

        return prefix;
    }

    public static String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();

        if (strs == null || strs.length == 0 || strs[0] == null) return result.toString();
        if (strs.length == 1) return strs[0];

        int[] card = new int[128];
        int[] index = new int[128];
        int start = 0;

        do {

            for (int i = 0; i < strs.length; i++) {
                String item = strs[i];

                // filter empty situation
                if (item == null || item.isEmpty() || (start == item.length())) {
                    return result.toString();
                }

                char current = item.charAt(start);

                if (index[current] == start) {
                    card[current]++;
                } else {
                    index[current] = start;
                    card[current] = 1;
                }
            }

            if (card[strs[0].charAt(start)] == strs.length) {
                result.append(strs[0].charAt(start));
            } else {
                return result.toString();
            }

            start++;

        } while (true);
    }
}
