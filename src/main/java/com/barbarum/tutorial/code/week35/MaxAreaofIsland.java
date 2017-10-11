package com.barbarum.tutorial.code.week35;

/**
 * https://leetcode.com/contest/leetcode-weekly-contest-53/problems/max-area-of-island/
 */
public class MaxAreaofIsland {

    public static void main(String[] args) {
        System.out.println(maxAreaOfIsland(new int[][]{new int[]{0, 0, 0, 0, 0, 0, 0, 0}}));
        System.out.println(maxAreaOfIsland(new int[][]{new int[]{0, 0, 0, 0, 0, 0, 1, 0}, new int[]{0, 0, 0, 0, 0, 0, 1, 0}}));
        System.out.println(maxAreaOfIsland(new int[][]{
                new int[]{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                new int[]{0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[]{0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                new int[]{0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}}));
    }

    public static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int max = 0;

        for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
            int row[] = grid[rowIndex];
            for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
                // Skip 0
                if (row[columnIndex] == 0) continue;
                max = Math.max(maxAreaOFIsland(grid, rowIndex, columnIndex), max);
            }
        }

        return max;
    }

    private static int maxAreaOFIsland(int[][] grid, int row, int column) {
        if (row < 0 || row >= grid.length || column < 0 || column >= grid[row].length || grid[row][column] == 0) {
            return 0;
        }
        grid[row][column] = 0;
        return 1
                + maxAreaOFIsland(grid, row, column - 1) + maxAreaOFIsland(grid, row, column + 1)
                + maxAreaOFIsland(grid, row - 1, column) + maxAreaOFIsland(grid, row + 1, column);
    }

}
