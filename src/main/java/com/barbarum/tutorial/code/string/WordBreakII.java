package com.barbarum.tutorial.code.string;


import java.util.*;

public class WordBreakII {

    /**
     * Break word by dynamic programming.
     */
    public static List<String> makeBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return Collections.emptyList();
        }

        List<Integer> cache[] = new ArrayList[s.length() + 1];
        cache[0] = new ArrayList<>();

        breakWords(s, wordDict, cache);

        if (cache[s.length()] == null) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        fillSentence(s, s.length(), result, cache, new LinkedList<>());

        return result;
    }

    private static void breakWords(String s, List<String> dict, List<Integer>[] cache) {
        for (int start = 0; start < s.length(); start++) {
            if (cache[start] == null) {
                continue;
            }

            for (String word : dict) {
                int end = start + word.length();
                if (end > s.length() || !word.equals(s.substring(start, end))) {
                    continue;
                }
                if (cache[end] == null) {
                    cache[end] = new ArrayList<>();
                }
                cache[end].add(start);
            }
        }
    }

    private static void fillSentence(String s, int length, List<String> result, List<Integer>[] dp, Deque<String> workingCopy) {
        if (length == 0) {
            addSentenceIntoCollection(workingCopy, result);
            return;
        }
        for (int start : dp[length]) {
            workingCopy.push(s.substring(start, length));
            fillSentence(s, start, result, dp, workingCopy);
            workingCopy.pop();
        }
    }

    private static void addSentenceIntoCollection(Deque<String> words, List<String> result) {
        if (words.isEmpty()) {
            return;
        }

        StringBuilder builder = new StringBuilder();

        for (String word : words) {
            builder.append(word).append(" ");
        }

        result.add(builder.substring(0, builder.length() - 1));
    }

    public static void main(String[] args) {
        System.out.println(WordBreakII.makeBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
        System.out.println(makeBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")));
        System.out.println(makeBreak("aaaaaa", Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")));

    }
}
