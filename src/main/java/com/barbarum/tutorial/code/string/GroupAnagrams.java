package com.barbarum.tutorial.code.string;

import java.util.*;

public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return Collections.emptyList();
        }

        Map<String, List<String>> cache = new HashMap<>();

        group(strs, cache);

        return convert(cache);
    }

    private static void group(String[] anagrams, Map<String, List<String>> cache) {
        for (int i = 0; i < anagrams.length; i++) {
            String key = rearrange(anagrams[i]);
            cache.computeIfAbsent(key, k -> new ArrayList<>())
                    .add(anagrams[i]);
        }
    }

    private static String rearrange(String anagram) {
        if (anagram.length() == 0) {
            return anagram;
        }

        char result[] = anagram.toCharArray();
        char copy[] = anagram.toCharArray();

        // Merge sort
        mergeSort(result, 0, result.length - 1, copy);

        return new String(result);
    }

    private static void mergeSort(char[] result, int start, int end, char[] copy) {
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;

        mergeSort(copy, start, mid, result);
        mergeSort(copy, mid + 1, end, result);

        merge(result, start, mid, end, copy);
    }

    private static void merge(char[] result, int start, int mid, int end, char[] copy) {
        int firstHalfIndex = start;
        int secondHalfIndex = mid + 1;
        int current = start;

        while (firstHalfIndex <= mid && secondHalfIndex <= end) {
            result[current++] = copy[firstHalfIndex] < copy[secondHalfIndex] ? copy[firstHalfIndex++] : copy[secondHalfIndex++];
        }
        while (firstHalfIndex <= mid) {
            result[current++] = copy[firstHalfIndex++];
        }
        while (secondHalfIndex <= end) {
            result[current++] = copy[secondHalfIndex++];
        }
    }

    private static List<List<String>> convert(Map<String, List<String>> cache) {
        return new ArrayList<>(cache.values());
    }

    public static void main(String args[]) {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
