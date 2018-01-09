package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

public class FindElementInSortedRotatedArray {

    /**
     * Find the given element in a sorted rotated array.
     *
     * @param nums   the given sorted rotated array.
     * @param target the given number.
     * @return index of the given element in the array if found, otherwise -1.
     */
    public static int findElement(int nums[], int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int pivot = findPivot(nums);

        // Eliminate the case if pivot is 0.
        int previousPivot = (pivot - 1 + nums.length) % nums.length;
        if (target < nums[pivot] || target > nums[previousPivot]) {
            return -1;
        }
        if (target == nums[pivot]) {
            return pivot;
        }
        if (target == nums[previousPivot]) {
            return previousPivot;
        }

        return target >= nums[0] ? binarySearch(nums, target, 0, previousPivot) : binarySearch(nums, target, pivot, nums.length - 1);
    }

    private static int binarySearch(int[] nums, int target, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (target == nums[mid]) {
            return mid;
        }

        return target < nums[mid] ? binarySearch(nums, target, start, mid - 1) : binarySearch(nums, target, mid + 1, end);
    }

    private static int findPivot(int[] nums) {
        if (nums[0] < nums[nums.length - 1]) {
            return 0;
        }

        return findPivotIndex(nums, 0, nums.length - 1);
    }

    private static int findPivotIndex(int[] nums, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (mid > 0 && nums[mid - 1] > nums[mid]) {
            return mid;
        }
        if (mid < nums.length - 1 && nums[mid] > nums[mid + 1]) {
            return mid + 1;
        }

        return nums[start] >= nums[mid] ? findPivotIndex(nums, start, mid - 1) : findPivotIndex(nums, mid + 1, end);
    }

    public static int findElementV2(int[] num, int target) {
        if (num == null || num.length == 0) {
            return -1;
        }
        if (num.length == 1) {
            return num[0] == target ? 0 : -1;
        }
        return findElementV2(num, 0, num.length - 1, target);
    }

    private static int findElementV2(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            return mid;
        }

        if (isSorted(nums, start, mid)) {
            return isRangeOf(nums, start, mid, target)
                    ? findElementV2(nums, start, mid - 1, target)
                    : findElementV2(nums, mid + 1, end, target);
        } else {
            return isRangeOf(nums, mid, end, target)
                    ? findElementV2(nums, mid + 1, end, target)
                    : findElementV2(nums, start, mid - 1, target);
        }
    }

    private static boolean isSorted(int[] num, int start, int end) {
        return num[start] < num[end];
    }

    private static boolean isRangeOf(int[] num, int start, int end, int target) {
        return num[start] <= target && target <= num[end];
    }


    public static void main(String args[]) {
        int nums[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        for (int num : nums) {
            ArrayRotate.rotate(nums, 1);
            PrintUtil.println("Input -> ", nums);
            PrintUtil.println("Output -> ", findElement(nums, 9));
        }

    }
}
