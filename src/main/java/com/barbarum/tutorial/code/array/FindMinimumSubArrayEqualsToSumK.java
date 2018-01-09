package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

import java.util.HashMap;

public class FindMinimumSubArrayEqualsToSumK {

    public static int find(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int minimum = nums.length + 1;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sum == k) {
                minimum = Math.min(minimum, i + 1); // (i+1) - 0
                continue;
            }

            int diff = sum - k;

            if (map.containsKey(diff)) {
                minimum = Math.min(minimum, i - map.get(diff)); // (i+1) - (map.get(diff)+1)
            } else {
                map.put(sum, i);
            }
        }

        return minimum != nums.length + 1 ? minimum : -1;
    }

    public static void main(String args[]) {

        int nums[] = new int[]{2, 3, 1, 2, 4, 3};

        PrintUtil.println("Input -> ", nums);
        PrintUtil.println("Output -> ", find(nums, 7));
    }
}
