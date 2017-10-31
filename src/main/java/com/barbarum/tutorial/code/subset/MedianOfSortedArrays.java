package com.barbarum.tutorial.code.subset;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * <p>
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * The median is (2 + 3)/2 = 2.5
 */
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

        int mLength = nums1.length;
        int nLength = nums2.length;

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

        for (int i = 0; i < (totalLength - 1) / 2; i++) {
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

    private static int findBiggestNumber(int[] a, int[] b, int aStart, int bStart, int step) {
        int num = Integer.MIN_VALUE;
        int aIndex = aStart;
        int bIndex = bStart;
        int loop = step;

        while ((loop--) >= 0) {
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
