package com.barbarum.tutorial.leetcode;

public class MedianOfSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            if (nums2 == null || nums2.length == 0) {
                throw new IllegalArgumentException();
            } else {
                int medianIndex = nums2.length / 2;
                return nums2.length % 2 == 0 ? (nums2[medianIndex - 1] + nums2[medianIndex]) / 2.0 : nums2[medianIndex];
            }
        }
        if (nums2 == null || nums2.length == 0) {
            int medianIndex = nums1.length / 2;
            return nums1.length % 2 == 0 ? (nums1[medianIndex - 1] + nums1[medianIndex]) / 2.0 : nums1[medianIndex];
        }

        int mLength = nums1.length, nLength = nums2.length;

        int[] minIndex = new int[2];
        int[] maxIndex = new int[]{mLength - 1, nLength - 1};

        for (int i = 0; i < (mLength + nLength - 1) / 2; i++) {
            if (minIndex[0] > maxIndex[0] || minIndex[1] > maxIndex[1]) {
                break;
            }

            // Shrink min value
            if (nums1[minIndex[0]] < nums2[minIndex[1]]) {
                minIndex[0]++;
            } else {
                minIndex[1]++;
            }

            // Shrink max value
            if (minIndex[0] > maxIndex[0]) {
                maxIndex[1]--;
                break;
            }
            if (minIndex[1] > maxIndex[1]) {
                maxIndex[0]--;
                break;
            }

            if (nums1[maxIndex[0]] > nums2[maxIndex[1]]) {
                maxIndex[0]--;
            } else {
                maxIndex[1]--;
            }
        }

        if (minIndex[0] > maxIndex[0]) {
            int medianIndex = (minIndex[1] + maxIndex[1]) / 2;
            return (minIndex[1] + maxIndex[1]) % 2 != 0 ? (nums2[medianIndex] + nums2[medianIndex + 1]) / 2.0 : nums2[medianIndex];
        } else if (minIndex[1] > maxIndex[1]) {
            int medianIndex = (minIndex[0] + maxIndex[0]) / 2;
            return (minIndex[0] + maxIndex[0]) % 2 != 0 ? (nums1[medianIndex] + nums1[medianIndex + 1]) / 2.0 : nums1[medianIndex];
        } else {
            return (nums1[minIndex[0]] + nums2[minIndex[1]]) / 2.0;
        }
    }

    public static double findMedianSortedArraysWithCursor(int[] nums1, int[] nums2) {
        int[] cursors = new int[]{nums1.length - 1, nums2.length - 1};

        int totalLength = nums1.length + nums2.length;

        for (int i = 0; i < (nums1.length + nums2.length - 1) / 2; i++) {
            if (cursors[0] < 0) {
                cursors[1]--;
                continue;
            }
            if (cursors[1] < 0) {
                cursors[0]--;
                continue;
            }
            cursors[nums1[cursors[0]] < nums2[cursors[1]] ? 1 : 0]--;
        }

        if (totalLength % 2 == 1) {
            return findBiggestNumber(nums1, nums2, cursors[0], cursors[1], 0);
        } else {
            return (findBiggestNumber(nums1, nums2, cursors[0], cursors[1], 0) + findBiggestNumber(nums1, nums2, cursors[0], cursors[1], 1)) / 2.0;
        }
    }

    private static int findBiggestNumber(int[] a, int[] b, int aIndex, int bIndex, int step) {
        int num = Integer.MIN_VALUE;

        while ((step--) >= 0) {
            if (aIndex < 0) {
                num = b[bIndex--];
                continue;
            }
            if (bIndex < 0) {
                num = a[aIndex--];
                continue;
            }
            num = a[aIndex] < b[bIndex] ? b[bIndex--] : a[aIndex--];
        }

        return num;
    }
}
