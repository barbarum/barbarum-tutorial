package com.barbarum.tutorial.code.number.sum;

import java.util.Arrays;

/**
 * http://www.cnblogs.com/jcliBlogger/p/4736809.html
 */
public class ThreeSumSmaller {

    public static int findThreeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);

        int result = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            // remove duplication
            if (nums[i] > target) {
                break;
            }

            int start = i + 1;
            int end = nums.length - 1;

            while (start < end && (nums[i] + nums[start]) <= target) {
                if (nums[i] + nums[start] + nums[end] >= target) {
                    end--;
                } else {
                    result += end - start;
                    start++;
                }
            }
        }

        return result;
    }


    public static void main(String args[]) {
        System.out.println(findThreeSumSmaller(new int[]{-2, 0, 1, 3}, 2));
    }
}
