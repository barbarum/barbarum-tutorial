package com.barbarum.tutorial.code.string;

import com.barbarum.tutorial.util.InputUtil;
import com.barbarum.tutorial.util.PrintUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    public static Boolean makeBreak(String word, List<String> dict) {
        if (word == null || word.isEmpty()) {
            return true;
        }

        Set<String> newDict = new HashSet<>(dict);
        int maxLength = findMaxLength(dict);
        boolean cache[] = new boolean[word.length() + 1];
        cache[0] = true;

        return makeBreak(word, newDict, maxLength, cache);
    }

    private static boolean makeBreak(String word, Set<String> dict, int maxLength, boolean cache[]) {
        int length = word.length();

        for (int i = 0; i < length; i++) {
            if (!cache[i]) {
                continue;
            }

            for (int j = i + 1; j <= length && (j - i) <= maxLength; j++) {
                if (!cache[j] && dict.contains(word.substring(i, j))) {
                    cache[j] = true;
                }
            }

            if (cache[length]) {
                return true;
            }
        }

        return false;
    }

    private static int findMaxLength(List<String> dict) {
        int maxLength = -1;
        for (String item : dict) {
            maxLength = Math.max(maxLength, item.length());
        }
        return maxLength;
    }

    public static void main(String args[]) {
        PrintUtil.println("ilike", InputUtil.convertToStringList("i, like, sam, sung, samsung, mobile, ice, \n" +
                "  cream, icecream, man, go, mango"), WordBreak::makeBreak);

        PrintUtil.println("ilikesamsung", InputUtil.convertToStringList("i, like, sam, sung, samsung, mobile, ice, \n" +
                "  cream, icecream, man, go, mango"), WordBreak::makeBreak);

        PrintUtil.println("abcde", InputUtil.convertToStringList("a, ab, b, c"), WordBreak::makeBreak);
    }
}
