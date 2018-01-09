package com.barbarum.tutorial.code.string;

import java.util.*;

public class DNASequences {

    public static Collection<String> find(String s) {
        int k = 10;
        if (s == null || s.length() < k) {
            return Collections.emptyList();
        }

        Set<String> cache = new HashSet<>();
        Set<String> result = new HashSet<>();

        for (int i = k; i < s.length(); i++) {
            String sequence = s.substring(i - k, i);
            if (cache.contains(sequence)) {
                result.add(sequence);
            } else {
                cache.add(sequence);
            }
        }

        return result;
    }


    public static void main(String args[]) {
        System.out.println(find("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
}
