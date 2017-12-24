package com.barbarum.tutorial.code.number;

import com.barbarum.tutorial.util.InputUtil;
import com.google.common.base.Preconditions;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindKthSmallestInMSortedArray {

    /**
     * Time complexity: average - O(n + klogn), worst - O(n + klogn)
     *
     * @param matrix the given matrix.
     * @param k      the given kth smallest index.
     * @return the given kth smallest value from the matrix.
     */
    public static int findKthSmallest(int[][] matrix, int k) {
        Preconditions.checkArgument(k >= 1, "K must be a positive number.");
        List<Node> elements = IntStream.range(0, matrix.length)
                .filter((row) -> InputUtil.isValid(matrix, row))
                .mapToObj(row -> Node.createNode(matrix, row, 0))
                .collect(Collectors.toList());

        // Build up heap.
        // TC: average - O(n), worst - O(n)
        PriorityQueue<Node> queue = new PriorityQueue<>(elements);

        // Time complexity: average - O(klogn), worst - O(klogn)
        for (int i = 1; i < k; i++) {
            Node item = queue.poll();

            if (!item.isEnd()) {
                queue.offer(item.forward(matrix));
            }
        }

        return queue.peek().getValue();
    }


//    public static int findKthSmallestWithBigCollection(int matrix, int k) {
//
//    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                new int[]{5, 12, 14},
                new int[]{7, 11, 13},
                new int[]{6, 13, 15},
                new int[]{5, 16, 19},
                new int[]{4, 17, 20},
                new int[]{3, 17, 20},
                new int[]{2, 17, 20}
        };

        StringBuilder builder = new StringBuilder();
        IntStream.rangeClosed(1, matrix.length * matrix[0].length)
                .forEach((item) -> builder.append(findKthSmallest(matrix, item)).append(" "));

        System.out.println(builder.toString());
    }

    private static class Node implements Comparable<Node> {
        private int value;
        private final int row;
        private int column;
        private final int end;

        public static Node createNode(int[][] matrix, int row, int column) {
            return new Node(matrix[row][column], row, column, matrix[row].length - 1);
        }

        Node(int value, int row, int column, int end) {
            this.value = value;
            this.row = row;
            this.column = column;
            this.end = end;
        }

        /**
         * Move one step forward from the matrix.
         */
        Node forward(int[][] matrix) {
            this.column++;
            this.value = matrix[this.row][this.column];
            return this;
        }

        int getValue() {
            return value;
        }

        int getRow() {
            return row;
        }

        int getColumn() {
            return column;
        }

        int getEnd() {
            return end;
        }

        boolean isEnd() {
            return this.getColumn() >= this.getEnd();
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.getValue(), o.getValue());
        }
    }
}
