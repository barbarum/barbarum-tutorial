package com.barbarum.tutorial.code.wording;

import java.util.*;

public class WordBreakIIDP {

    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> dp[] = new ArrayList[s.length() + 1];
        dp[0] = new ArrayList<>();

        breakWords(s, wordDict, dp);

        if (dp[s.length()] == null) {
            return Collections.emptyList();
        }

        List<String> result = new LinkedList<>();
        fillSentence(dp, s.length(), result, new LinkedList<>());

        return result;
    }

    private static void breakWords(String s, List<String> dict, List<String>[] dp) {
        for (int start = 0; start < s.length(); start++) {
            if (dp[start] == null) {
                continue;
            }

            for (String word : dict) {
                int end = start + word.length();
                if (end > s.length() || !word.equals(s.substring(start, end))) {
                    continue;
                }
                if (dp[end] == null) {
                    dp[end] = new ArrayList<>();
                }
                dp[end].add(word);
            }
        }
    }

    private static void fillSentence(List<String>[] dp, int index, List<String> result, LinkedList<String> workingCopy) {
        if (index == 0) {
            addSentenceIntoCollection(workingCopy, result);
            return;
        }
        for (String word : dp[index]) {
            workingCopy.addFirst(word);
            fillSentence(dp, index - word.length(), result, workingCopy);
            workingCopy.removeFirst();
        }
    }

    private static void addSentenceIntoCollection(LinkedList<String> words, List<String> result) {
        StringBuilder builder = new StringBuilder();

        for (String word : words) {
            builder.append(word).append(" ");
        }

        result.add(builder.length() > 0 ? builder.substring(0, builder.length() - 1) : builder.toString());
    }

    public static void main(String args[]) {
        System.out.println(wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
    }
}
