package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.ArrayUtil;

public class FindElementInSortedMatrix {

    public static boolean contains(int[][] matrix, int element) {
        int len = matrix.length;

        if (element < matrix[0][0] || element > matrix[len - 1][len - 1]) {
            return false;
        }

        int row = findRowToSearch(matrix, 0, len - 1, element);

        System.out.println(String.format("Row (%s) -> %s", row, element));
        return searchRow(matrix, row, 0, len - 1, element);
    }

    private static boolean searchRow(int[][] matrix, int row, int start, int end, int element) {
        if (start == end) {
            return element == matrix[row][start];
        }

        int mid = (start + end) / 2;

        if (matrix[row][mid] == element) {
            return true;
        }

        if (matrix[row][mid] < element) {
            return searchRow(matrix, row, mid + 1, end, element);
        }

        return searchRow(matrix, row, start, mid - 1, element);
    }


    private static int findRowToSearch(int[][] matrix, int start, int end, int element) {
        if (start == end) {
            return start;
        }

        int mid = (start + end) / 2;

        if (matrix[mid][0] > element) {
            return findRowToSearch(matrix, start, mid - 1, element);
        }
        if (matrix[mid][0] == element || matrix[mid][matrix.length - 1] >= element) {
            return mid;
        }
        return findRowToSearch(matrix, mid + 1, end, element);
    }

    public static void main(String args[]) {
        int matrix[][] = ArrayUtil.buildIntArrayMatrix("1,2,3", "4,5,6", "7,8,9");

        for (int i = 0; i < 10; i++) {
            System.out.println(i + "->" + contains(matrix, i));
        }
    }
}
