package com.barbarum.tutorial.code.randomaccess;

import java.util.ArrayList;
import java.util.List;

public class KnightProbability {

    public static double knightProbability(int n, int k, int r, int c) {
        return knightProbability(n, k, r, c, new double[n][n][k]);
    }

    private static double knightProbability(int n, int k, int r, int c, double[][][] dp) {
        if (!isValid(n, r, c)) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        if (dp[r][c][k - 1] > 0.000001) {
            return dp[r][c][k - 1];
        }


        double result = 0;
        List<int[]> validMoves = calculateNextValidMove(n, r, c);
        for (int[] move : validMoves) {
            result += (1.0 / 8) * knightProbability(n, k - 1, move[0], move[1], dp);
        }

        // dp
        dp[r][c][k - 1] = result;
        dp[c][r][k - 1] = result;
        dp[n - 1 - r][c][k - 1] = result;
        dp[n - 1 - c][r][k - 1] = result;
        dp[r][n - 1 - c][k - 1] = result;
        dp[c][n - 1 - r][k - 1] = result;
        dp[n - 1 - r][n - 1 - c][k - 1] = result;
        dp[n - 1 - c][n - 1 - r][k - 1] = result;

        return result;
    }

    private static boolean isValid(int n, int r, int c) {
        return (r >= 0 && r < n) && (c >= 0 && c < n);
    }


    private static List<int[]> calculateNextValidMove(int n, int r, int c) {
        int[][] offset = new int[][]{
                new int[]{-2, -1},
                new int[]{-2, +1},
                new int[]{-1, -2},
                new int[]{-1, +2},
                new int[]{+1, -2},
                new int[]{+1, +2},
                new int[]{+2, -1},
                new int[]{+2, +1},
        };

        List<int[]> result = new ArrayList<>();
        for (int[] item : offset) {
            if (isValid(n, r + item[0], c + item[1])) {
                result.add(new int[]{r + item[0], c + item[1]});
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(knightProbability(3, 2, 0, 0));
    }
}
