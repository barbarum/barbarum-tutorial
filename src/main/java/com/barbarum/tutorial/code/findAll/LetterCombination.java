package com.barbarum.tutorial.code.findAll;

import java.util.*;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 */
public class LetterCombination {

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }

        String[] dict = new String[]{" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        LinkedList<String> result = new LinkedList<>();
        result.add("");

        for (int i = 0; i < digits.length(); i++) {
            int num = Character.getNumericValue(digits.charAt(i));
            while (result.peek().length() == i) {
                String temp = result.pop();
                for (char c : dict[num].toCharArray()) {
                    result.add(temp + c);
                }
            }
        }
        return result;
    }
}
