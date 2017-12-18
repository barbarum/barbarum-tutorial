package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

public class AlternativeOrder {

    /**
     * @param nums the given numbers.
     */
    public static void rearrange(int nums[]) { // -1, 3
        boolean flag;

        for (int i = 0; i < nums.length; i++) {
            flag = i % 2 == 0; // 0 -> true
            int next = findRightNum(nums, i, flag); // -1,3; 0, true

            if (next == -1) {
                return;
            }

            rightRotate(nums, i, next);
        }
    }

    private static void rightRotate(int[] nums, int start, int end) {
        int temp = nums[end];
        for (int i = end - 1; i >= start; i--) {
            nums[i + 1] = nums[i];
        }
        nums[start] = temp;
    }

    private static int findRightNum(int[] nums, int start, boolean flag) {
        for (int i = start; i < nums.length; i++) {
            if ((flag && nums[i] > 0) || (!flag && nums[i] < 0)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String args[]) {

        int nums[] = new int[]{-1, 3, 2, 4, 5, -6, 7, -9};
        PrintUtil.println("Input -> ", nums);
        rearrange(nums);
        PrintUtil.println("Output -> ", nums);

    }
}
