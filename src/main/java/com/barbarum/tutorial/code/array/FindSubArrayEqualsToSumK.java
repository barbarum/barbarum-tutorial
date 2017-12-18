package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

import java.util.Arrays;
import java.util.HashMap;

public class FindSubArrayEqualsToSumK {


    public static void find(int[] nums, int k, int[] result) {

        //<sum, i>
        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sum == k) {
                result[0] = 0;
                result[1] = i;
                return;
            }

            int subSum = sum - k;

            if (map.containsKey(subSum)) {
                result[0] = map.get(subSum) + 1;
                result[1] = i;
            } else {
                map.put(sum, i);
            }
        }
    }

    public static void main(String args[]) {
        int[] result = new int[2];
        Arrays.fill(result, -1);

        int[] nums = new int[]{1, 4, 20, 3, 10, 5};

        find(nums, 12, result);

        PrintUtil.println("Input -> ", nums);
        PrintUtil.println("Output ->", result);

    }

}
