package com.barbarum.tutorial.code.string;


import java.util.*;

/**
 * [
 * ['A','B','C','E'],
 * ['S','B','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * SEE -> true
 */
public class WordSearch {

    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.isEmpty()) {
            return false;
        }
        Queue<Node> queue = findCharacterAndQueue(board, word.charAt(0));
        return search(board, queue, word);
    }

    /**
     * [
     * ['A','B','C','E'],
     * [0,'F','C',0],
     * ['A','D','E','E']
     * ]
     * SEE -> true
     * <p>
     * S 1, 0
     * S 1, 3
     */
    private static boolean search(char[][] board, Queue<Node> queue, String word) {

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.depth == word.length()) {
                return true;
            }

            findAdjacentCharacterAndReQueue(board, queue, node, word);
        }

        return false;
    }

    /**
     * [
     * ['A','B','C',0],
     * [0,'F','C',0],
     * ['A','D','E','E']
     * ]
     * SEE -> true
     * <p>
     * S 1, 0
     * S 1, 3
     * <p>
     * E 0, 3, 2
     */
    private static void findAdjacentCharacterAndReQueue(char[][] board, Queue<Node> queue, Node node, String word) {
        char c = word.charAt(node.depth - 1);

        validateAndQueue(board, queue, node.row - 1, node.column, node, c); // 0, 3, 2, 'E'
        validateAndQueue(board, queue, node.row + 1, node.column, node, c); // 2, 0, 2, 'E'
        validateAndQueue(board, queue, node.row, node.column - 1, node, c); // 1, -1, 2, 'E'
        validateAndQueue(board, queue, node.row, node.column + 1, node, c); // 1, 1, 2, 'E'
    }

    private static void validateAndQueue(char[][] board, Queue<Node> queue, int row, int column, Node node, char expected) {
        if (row < 0 || row >= board.length || column < 0 || column >= board[row].length) {
            return;
        }
        if (board[row][column] != expected || node.has(row, column)) {
            return;
        }
        queue.add(Node.clone(node).move(row, column));
    }

    /**
     * [
     * ['A','B','C','E'],
     * [0,'F','C',0],
     * ['A','D','E','E']
     * ]
     * SEE -> true
     * <p>
     * S 1, 0
     * S 1, 3
     */
    private static Queue<Node> findCharacterAndQueue(char[][] board, char c) {
        Queue<Node> result = new LinkedList<>();
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (board[row][column] == c) {
                    result.add(new Node(row, column));
                }
            }
        }
        return result;
    }

    private static class Node {
        int row;
        int column;
        int depth;
        Map<Integer, Integer> characters = new HashMap<>();

        private Node() {
        }

        public Node(int row, int column) {
            this.move(row, column);
        }

        public boolean has(int row, int column) {
            return this.characters.containsKey(row) && this.characters.get(row) == column;
        }

        public Node move(int row, int column) {
            this.row = row;
            this.column = column;
            this.depth++;
            this.characters.put(row, column);

            return this;
        }

        public static Node clone(Node node) {
            Node newNode = new Node();
            newNode.row = node.row;
            newNode.column = node.column;
            newNode.depth = node.depth;
            newNode.characters = new HashMap<>(node.characters);
            return newNode;
        }
    }

    public static void main(String args[]) {
        System.out.println(exist(new char[][]{
                new char[]{'A', 'B', 'C', 'E'},
                new char[]{'A', 'B', 'C', 'E'},
                new char[]{'A', 'D', 'E', 'E'}
        }, "BCCB"));
    }
}
