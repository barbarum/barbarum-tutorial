package com.barbarum.tutorial.code.number.sum;

/**
 * https://leetcode.com/problems/container-with-most-water/description/
 */
public class WaterContainer {

    public static int maxArea(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        if (height.length == 2) {
            return Math.min(height[0], height[1]);
        }

        int start = 0;
        int end = height.length - 1;
        int result = 0;

        while (start < end) {
            result = Math.max(result, (end - start) * Math.min(height[start], height[end]));

            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }

        return result;
    }
}
