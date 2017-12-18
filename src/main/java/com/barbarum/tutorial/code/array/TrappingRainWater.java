package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

public class TrappingRainWater {

    public static int trap(int[] towers) {

        int[] leftTaller = findLeftTaller(towers);
        int[] rightTaller = findRightTaller(towers);

        return calculate(towers, leftTaller, rightTaller);
    }

    private static int calculate(int[] towers, int[] leftTaller, int[] rightTaller) {
        int result = 0;

        for (int i = 0; i < towers.length; i++) {
            int left = leftTaller[i];
            int right = rightTaller[i];

            if (left == -1 || right == -1) {
                continue;
            }

            result += Math.min(left, right) - towers[i];
        }

        return result;
    }

    private static int[] findLeftTaller(int[] towers) {
        int highest = 0;
        int result[] = new int[towers.length];

        for (int i = 0; i < towers.length; i++) {
            result[i] = towers[i] >= highest ? -1 : highest;
            highest = Math.max(highest, towers[i]);
        }

        return result;
    }


    private static int[] findRightTaller(int[] towers) {
        int highest = 0;
        int result[] = new int[towers.length];

        for (int i = towers.length - 1; i >= 0; i--) {
            result[i] = towers[i] >= highest ? -1 : highest;
            highest = Math.max(highest, towers[i]);
        }

        return result;
    }


    public static void main(String args[]) {
        PrintUtil.println(new int[]{1, 5, 2, 3, 1, 7, 2, 4}, TrappingRainWater::trap);
    }
}
