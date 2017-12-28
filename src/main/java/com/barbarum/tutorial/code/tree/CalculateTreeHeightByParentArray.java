package com.barbarum.tutorial.code.tree;

import java.util.Arrays;

public class CalculateTreeHeightByParentArray {

    public static int calculateHeight(int[] parents) {
        if (parents == null || parents.length == 0) {
            return 0;
        }

        int[] depths = new int[parents.length]; // [1, -1]
        fillDepths(depths);
        calculateDepths(parents, depths);
        return findMaximumDepth(depths);
    }

    private static void calculateDepths(int[] parents, int[] depths) {
        for (int i = 0; i < parents.length; i++) {
            calculateDepth(i, parents, depths);
        }
    }

    private static void calculateDepth(int value, int[] parents, int[] depths) {
        if (depths[value] != -1) {
            return;
        }
        if (parents[value] == -1) {
            depths[value] = 1;
            return;
        }
        if (depths[parents[value]] == -1) {
            calculateDepth(parents[value], parents, depths);
        }
        depths[value] = depths[parents[value]] + 1;
    }

    private static int findMaximumDepth(int[] depths) {
        int maximumDepth = 0;
        for (int depth : depths) {
            maximumDepth = Math.max(maximumDepth, depth);
        }
        return maximumDepth;
    }

    private static void fillDepths(int[] depths) {
        Arrays.fill(depths, -1);
    }
}
