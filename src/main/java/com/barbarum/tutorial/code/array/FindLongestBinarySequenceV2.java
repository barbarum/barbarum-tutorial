package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

public class FindLongestBinarySequenceV2 {

    public static int find(int[] nums) {
        int previousOfPrevious;
        int previous = -1;
        int current = -1;

        int index = -1;
        int maximum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                continue;
            }

            previousOfPrevious = previous;
            previous = current;
            current = i;

            if (previous != -1) {
                int distance = current - (previousOfPrevious + 1);
                if (distance > maximum) {
                    maximum = distance;
                    index = previous;
                }
            }
        }

        // Calculate the last one
        if (nums.length - (previous + 1) > maximum) {
            index = current;
        }


        return index;
    }

    public static void main(String args[]) {
        PrintUtil.println(new int[]{0, 1, 1, 1, 0, 1, 0}, FindLongestBinarySequenceV2::find);
        PrintUtil.println(new int[]{0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0}, FindLongestBinarySequenceV2::find);
        PrintUtil.println(new int[]{1, 1, 1, 1, 0, 1}, FindLongestBinarySequenceV2::find);
    }
}
