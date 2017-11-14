package com.barbarum.tutorial.code.wording;

import java.util.*;

/**
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<>(wordList);
        dict.remove(beginWord);

        if (!dict.contains(endWord)) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(beginWord, 1));

        return dfs(queue, dict, beginWord, endWord);
    }

    private int dfs(Queue<Node> queue, HashSet<String> dict, String beginWord, String endWord) {
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.word.equals(endWord)) {
                return node.depth;
            }

            queueDirectTransfer(queue, node, dict);
        }

        return 0;
    }

    private void queueDirectTransfer(Queue<Node> queue, Node node, HashSet<String> dict) {
        List<String> hits = new ArrayList<>();
        for (String word : dict) {
            if (canDirectTransfer(node.word, word)) {
                queue.add(new Node(word, node.depth + 1));
                hits.add(word);
            }
        }
        dict.removeAll(hits);
    }

    private boolean canDirectTransfer(String start, String end) {
        int diff = 0;

        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) != end.charAt(i)) {
                diff++;
            }
        }

        return diff == 1;
    }

    private static class Node {
        String word;
        int depth;

        public Node(String word, int depth) {
            this.word = word;
            this.depth = depth;
        }
    }

    public static void main(String args[]) {
        System.out.println(new WordLadder().ladderLength("leet", "code",
                Arrays.asList("lest", "leet", "lose", "code", "lode", "robe", "lost")));
    }
}
