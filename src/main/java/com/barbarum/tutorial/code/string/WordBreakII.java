package com.barbarum.tutorial.code.string;


import java.util.*;

public class WordBreakII {

    public static List<String> wordBreak(String s, List<String> wordDict) {

        List<String> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Set<String> dict = convertDict(wordDict);

        queue.add(new Node());

        fillResult(s, result, dict, queue);

        return result;
    }

    private static Set<String> convertDict(List<String> wordDict) {
        return new HashSet<>(wordDict);
    }

    private static void fillResult(String s, List<String> result, Set<String> dict, Queue<Node> queue) {

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println("=>" + node.value);
            if (node.size == s.length()) {
                result.add(node.value);
            } else {
                queueNextBreak(s, node, dict, queue);
            }
        }
    }

    private static void queueNextBreak(String s, Node node, Set<String> dict, Queue<Node> queue) {
        for (int tail = node.size + 1; tail <= s.length() && tail - node.size <= maxLength(dict); tail++) {
            String subString = s.substring(node.size, tail);
            if (dict.contains(subString)) {
                queue.add(new Node(node.value + (node.size == 0 ? "" : " ") + subString, tail));
            }
        }
    }

    private static int maxLength(Set<String> dict) {
        return dict.stream()
                .mapToInt(String::length)
                .max().orElse(0);
    }

    private static class Node {
        String value;
        int size;

        public Node() {
            this.value = "";
        }

        public Node(String value, int size) {
            this.value = value;
            this.size = size;
        }
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")));
    }
}
