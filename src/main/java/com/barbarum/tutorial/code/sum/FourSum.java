package com.barbarum.tutorial.code.sum;

import java.util.*;

public class FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        int end = nums.length - 1;

        for (int i = 0; i <= end; i++) {
            if (nums[i] + 3 * nums[end] < target) { // Too small
                continue;
            }
            if (nums[i] * 4 > target) { // Too Large
                break;
            }
            if (nums[i] * 4 == target) {
                if (i <= end - 3 && nums[i] == nums[i + 3]) {
                    result.add(Arrays.asList(nums[i], nums[i], nums[i], nums[i]));
                }
                break;
            }

            sum3(nums, target - nums[i], i + 1, nums.length - 1, result, nums[i]);

            while (i < end && nums[i] == nums[i + 1]) { // Duplication
                i++;
            }
        }

        return result;
    }

    private static void sum3(int[] nums, int target, int start, int end, List<List<Integer>> result, int sum4) {
        if (start >= end || nums[end] * 3 < target || nums[start] * 3 > target) {
            return;
        }

        for (int i = start; i <= end; i++) {
            if (nums[i] + 2 * nums[end] < target) { // Too small
                continue;
            }
            if (nums[i] * 3 > target) {// Too large
                break;
            }
            if (nums[i] * 3 == target) {
                if (i <= end - 2 && nums[i] == nums[i + 2]) {
                    result.add(Arrays.asList(sum4, nums[i], nums[i], nums[i]));
                }
                break;
            }

            sum2(nums, target - nums[i], i + 1, result, sum4, nums[i]);

            while (i < end && nums[i] == nums[i + 1]) { // Duplication
                i++;
            }
        }
    }

    private static void sum2(int[] nums, int target, int start, List<List<Integer>> result, int sum4, int sum3) {
        int low = start;
        int high = nums.length - 1;

        while (low < high) {
            if (nums[low] + nums[high] == target) {
                result.add(Arrays.asList(sum4, sum3, nums[low], nums[high]));

                while (++low < high && nums[low] == nums[low - 1]) ;
                while (low < --high && nums[high] == nums[high + 1]) ;

            } else if (nums[low] + nums[high] < target) {
                low++;
            } else {
                high--;
            }
        }
    }

    public static void main(String args[]) {
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }
}
