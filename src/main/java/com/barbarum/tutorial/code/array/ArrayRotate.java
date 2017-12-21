package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

import static com.barbarum.tutorial.util.InputUtil.swap;

public class ArrayRotate {

    // Reversal!!!
    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int sk = k % nums.length;

        if (sk == 0) {
            return;
        }

        reverse(nums, 0, sk - 1);
        reverse(nums, sk, nums.length - 1);

        reverse(nums, 0, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        int forward = start;
        int backward = end;

        while (forward < backward) {
            swap(nums, forward, backward);
            forward++;
            backward--;
        }
    }


    public static void main(String args[]) {
        int[] nums = new int[]{1, 2, 3, 4, 5};

        PrintUtil.println("Input ->", nums);

        rotate(nums, 1);
        PrintUtil.println("Rotate 1s ->", nums);

        nums = new int[]{1, 2, 3, 4, 5};
        rotate(nums, 2);
        PrintUtil.println("Rotate 2s ->", nums);

        nums = new int[]{1, 2, 3, 4, 5};
        rotate(nums, 3);
        PrintUtil.println("Rotate 3s ->", nums);

        nums = new int[]{1, 2, 3, 4, 5};
        rotate(nums, 4);
        PrintUtil.println("Rotate 4s ->", nums);

        nums = new int[]{1, 2, 3, 4, 5};
        rotate(nums, 5);
        PrintUtil.println("Rotate 5s ->", nums);
    }
}
