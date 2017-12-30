package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Dict;

import java.util.List;

public class PatternMatch {

    public static List<Integer> search(String text, String pattern) {
        Dict<Integer> dict = new Dict<>();
        for (int i = text.length() - 2; i >= 0; i--) {
            dict.insert(text, i, Integer::intValue);
        }
        List<Integer> result = dict.match(pattern);
        for (int i = 0; i < result.size(); i++) {
            result.set(i, result.get(i) - (pattern.length() - 1));
        }
        return result;
    }
}
