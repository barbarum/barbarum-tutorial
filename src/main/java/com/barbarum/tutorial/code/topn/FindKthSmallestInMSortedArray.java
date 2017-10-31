package com.barbarum.tutorial.code.topn;

import java.util.PriorityQueue;

import static java.util.Comparator.comparingInt;

public class FindKthSmallestInMSortedArray {

    public static int findKthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer[]> queue = new PriorityQueue<>(matrix.length,
                comparingInt(item -> matrix[item[0]][item[1]]));

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] != null && matrix[i].length > 0) {
                queue.offer(new Integer[]{i, 0});
            }
        }

        for (int i = 0; i < k - 1; i++) {
            Integer[] smallest = queue.poll();
            if ((smallest[1]++) < matrix[smallest[0]].length - 1) {
                queue.offer(smallest);
            }
        }

        return matrix[queue.peek()[0]][queue.peek()[1]];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                new int[]{10, 5, 9},
                new int[]{7, 11, 13},
                new int[]{6, 13, 15},
                new int[]{5, 16, 19},
                new int[]{4, 17, 20},
                new int[]{3, 17, 20},
                new int[]{2, 17, 20}
        };

        System.out.println(findKthSmallest(matrix, 6));
    }
}
