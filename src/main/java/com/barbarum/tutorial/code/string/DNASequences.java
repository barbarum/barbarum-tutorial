package com.barbarum.tutorial.code.string;

import java.util.*;

public class DNASequences {

    public static List<String> find(String s) {
        int k = 10;
        if (s == null || s.length() < k) {
            return Collections.emptyList();
        }

        Map<String, Integer> cache = new HashMap<>();
        List<String> result = new ArrayList<>();

        for (int i = k; i < s.length(); i++) {
            String sequence = s.substring(i - k, i);
            if (cache.containsKey(sequence)) {
                cache.put(sequence, cache.get(sequence) + 1);
                if (cache.get(sequence) == 2) {
                    result.add(sequence);
                }
            } else {
                cache.put(sequence, 1);
            }
        }

        return result;
    }


    public static void main(String args[]) {
        System.out.println(find("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
}
