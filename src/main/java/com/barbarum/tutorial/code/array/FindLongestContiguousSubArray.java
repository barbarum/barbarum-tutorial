package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

public class FindLongestContiguousSubArray {


    /**
     * Given an array with all distinct elements, find the length of the longest sub-array which has elements(not in any particular order) that could form a contiguous sequence.
     * <p>
     * Input -> 10, 12, 11, 94, 56, 32, 34, 36, 33, 35, 37
     * Expect -> 6 (32,34,36,33,35,37)
     * <p>
     *
     * @param nums
     * @return
     */
    public static int findLength(int nums[]) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            int minimum = nums[i];
            int maximum = nums[i];

            for (int j = i + 1; j < nums.length; j++) {
                minimum = Math.min(minimum, nums[j]);
                maximum = Math.max(maximum, nums[j]);

                int gap = maximum - minimum + 1;

                // if we found out gap is too bigger than current loop length, then break.
                if (gap > nums.length - i) {
                    break;
                }

                if ((gap == j - i + 1) && gap > result) {
                    result = gap;
                }
            }
        }

        return result;
    }

    public static void main(String args[]) {
        PrintUtil.println(new int[]{10, 12, 11, 94, 56, 32, 34, 36, 33, 35, 37,1000}, FindLongestContiguousSubArray::findLength);
        PrintUtil.println(new int[]{-1, 1, 2, 3, 4, 5}, FindLongestContiguousSubArray::findLength);
    }
}
