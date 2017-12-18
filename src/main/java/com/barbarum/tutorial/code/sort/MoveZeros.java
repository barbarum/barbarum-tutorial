package com.barbarum.tutorial.code.sort;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class MoveZeros {

    public static void move(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int firstZeroIndex = findFirstZeroIndex(nums, start, end);
            if (firstZeroIndex == -1) {
                break;
            }

            int lastNonZeroIndex = findLastNonZeroIndex(nums, end, firstZeroIndex + 1);
            if (lastNonZeroIndex == -1) {
                break;
            }

            nums[firstZeroIndex] = nums[lastNonZeroIndex];
            nums[lastNonZeroIndex] = 0;

            start = firstZeroIndex + 1;
            end = lastNonZeroIndex - 1;
        }
    }

    private static int findLastNonZeroIndex(int[] nums, int end, int start) {
        for (int i = end; i >= start; i--) {
            if (nums[i] != 0) {
                return i;
            }
        }
        return -1;
    }

    private static int findFirstZeroIndex(int[] nums, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        print(1);
        print(0);
        print(6, 4, 0, 5, 0, 0, 0, 1, 0);
        print(0, 0, 0, 5, 0, 0, 0, 1, 0);
        print(0, 0, 0, 5, 0, 0, 0, 0, 0);
        print(3, 2, 1);
        print(0, 3, 2, 1);
    }

    private static void print(int... nums) {
        Collection list = Arrays.stream(nums).boxed().collect(Collectors.toList());

        StringBuilder builder = new StringBuilder(list.toString()).append("->");

        move(nums);

        list = Arrays.stream(nums).boxed().collect(Collectors.toList());

        builder.append(list);

        System.out.println(builder.toString());
    }
}
