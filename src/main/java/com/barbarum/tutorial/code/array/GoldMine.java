package com.barbarum.tutorial.code.array;

public class GoldMine {

    public int calculateMaximum(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }

        int dp[][] = new int[map.length][map[0].length];
        fillDP(map, dp);

        return calculateMaximum(map, dp);
    }

    private int calculateMaximum(int[][] map, int[][] dp) {
        int maximum = 0;

        for (int i = 0; i < map.length; i++) {
            maximum = Math.max(maximum, calculate(map, i, 0, dp));
        }

        return maximum;
    }

    private void fillDP(int[][] map, int[][] dp) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                dp[i][j] = -1;
            }
        }
    }

    private int calculate(int[][] map, int row, int column, int[][] dp) {
        if (row < 0 || row == map.length || column == map[row].length) {
            return 0;
        }
        if (dp[row][column] != -1) {
            return dp[row][column];
        }

        int rightUp = calculate(map, row - 1, column + 1, dp);
        int right = calculate(map, row, column + 1, dp);
        int rightDown = calculate(map, row + 1, column + 1, dp);


        int maximum = Math.max(Math.max(rightUp, right), rightDown) + map[row][column];
        dp[row][column] = maximum;

        return maximum;
    }

    public static void main(String args[]) {
        GoldMine function = new GoldMine();
        int[][] mine = {{1, 3, 1, 5},
                {2, 2, 4, 1},
                {5, 0, 2, 3},
                {0, 6, 1, 2}};
        System.out.println("Output -> " + function.calculateMaximum(mine));
    }
}
