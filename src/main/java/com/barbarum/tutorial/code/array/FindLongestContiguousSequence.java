package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

import java.util.HashSet;
import java.util.Set;

public class FindLongestContiguousSequence {

    /**
     * Given an array with all distinct elements, find the length of the longest sub-array which has elements(not in any particular order) that could form a contiguous sequence.
     * <p>
     * Input -> 3, 100, 1, 200, 4, 2
     * Expect -> 4 (3,1,4,2)
     * <p>
     * Note: this issue doesn't require the sub-array is contiguous in the original array.
     * <p>
     * <p>
     * Using Hash!!!
     */
    public static int findLongestLength(int nums[]) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        Set<Integer> table = new HashSet<>();

        for (int num : nums) {
            table.add(num);
        }

        return findLongestLength(nums, table);
    }

    private static int findLongestLength(int nums[], Set<Integer> table) {
        int result = 0;

        for (int num : nums) {

            // If the num is the starting point.
            if (isStartPoint(table, num)) {

                int distance = 1;
                while (table.contains(num + distance)) {
                    distance++;
                }

                result = Math.max(result, distance);
            }
        }

        return result;
    }

    private static boolean isStartPoint(Set<Integer> table, int target) {
        return !table.contains(target - 1);
    }

    public static void main(String args[]) {
        PrintUtil.println(new int[]{4, 100, 1, 200, 2, 3}, FindLongestContiguousSequence::findLongestLength);
    }
}
