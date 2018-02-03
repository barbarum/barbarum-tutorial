package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

public class FindLongestBinarySequence {

    public static int find(int[] nums) {
        int[] ones = new int[nums.length];

        countLeftOnes(nums, ones);
        countRightOnes(nums, ones);

        return findMaximum(ones);
    }

    private static int findMaximum(int[] ones) {
        int maximum = 0;
        int index = -1;

        for (int i = 0; i < ones.length; i++) {
            if (ones[i] > maximum) {
                maximum = ones[i];
                index = i;
            }
        }

        return index;
    }

    private static void countLeftOnes(int[] nums, int[] table) {
        int currentOnes = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                table[i] += currentOnes;
                currentOnes = 0;
            } else {
                currentOnes++;
            }
        }
    }

    public static void countRightOnes(int[] nums, int[] table) {
        int currentOnes = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                table[i] += currentOnes;
                currentOnes = 0;
            } else {
                currentOnes++;
            }
        }
    }

    public static void main(String args[]) {
        PrintUtil.println(new int[]{0, 1, 1, 1, 0, 1, 0}, FindLongestBinarySequence::find);
        PrintUtil.println(new int[]{0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0}, FindLongestBinarySequence::find);
        PrintUtil.println(new int[]{1, 1, 1, 1, 0, 1}, FindLongestBinarySequence::find);
        PrintUtil.println(new int[]{1, 1, 1, 1, 1, 1}, FindLongestBinarySequence::find);
    }
}
