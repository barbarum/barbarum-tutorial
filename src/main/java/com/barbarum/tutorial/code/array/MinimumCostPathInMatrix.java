package com.barbarum.tutorial.code.array;

public class MinimumCostPathInMatrix {

    /**
     * [3, 2, 8]
     * [1, 9, 7]
     * [0, 5, 2]
     * [6, 4, 3]
     */
    public static int cost(int matrix[][], int row, int column) {
        if (matrix == null || matrix.length == 0) {
            return Integer.MAX_VALUE;
        }

        int dp[][] = new int[row + 1][column + 1];
        fillCost(matrix, row, column, dp);

        return dp[row][column];
    }

    private static void fillCost(int[][] matrix, int row, int column, int[][] dp) {
        dp[0][0] = matrix[0][0];

        for (int i = 1; i <= row; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }

        for (int j = 1; j <= column; j++) {
            dp[0][j] = dp[0][j - 1] + matrix[0][j];
        }

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                dp[i][j] = minimum(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + matrix[i][j];
            }
        }
    }

    private static int minimum(int left, int up, int diagonal) {
        return Math.min(Math.min(left, up), diagonal);
    }

    public static void main(String args[]) {

        int[][] matrix = new int[4][3];
        matrix[0] = new int[]{3, 2, 8};
        matrix[1] = new int[]{1, 9, 7};
        matrix[2] = new int[]{0, 5, 2};
        matrix[3] = new int[]{6, 4, 3};


        System.out.println(cost(matrix, 3, 2));

    }

}
