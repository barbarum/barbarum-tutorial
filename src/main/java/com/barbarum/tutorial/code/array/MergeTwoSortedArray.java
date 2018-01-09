package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

public class MergeTwoSortedArray {

    private static int INVALID_NUM = 0;

    /**
     * Merge two sorted  array, and the given array are:
     * <p>
     * {-3, 5, INVALID_NUM, 7, INVALID_NUM, 10, INVALID_NUM, 11, INVALID_NUM}
     * {-1, 2, 6, 12}
     * </p>
     *
     * @param a the given array A with invalid_num
     * @param b this given array B to insert.
     */
    public static void merge(int[] a, int[] b) {

        // Move invalid num and return the start point contains the first element
        int aStart = moveInvalidNumToEnd(a);

        merge(a, b, aStart, 0);
    }

    private static int moveInvalidNumToEnd(int[] a) {
        int end = a.length;

        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] != INVALID_NUM) {
                a[--end] = a[i];
            }
        }

        return end;
    }

    private static void merge(int[] a, int[] b, int aStart, int bStart) {
        int invalidNumberIndex = 0;
        int i = aStart, j = bStart;

        while (i < a.length && j < b.length) {
            a[invalidNumberIndex++] = a[i] < b[j] ? a[i++] : b[j++];
        }

        // in case there are more date in array b.
        while (j < b.length) {
            a[invalidNumberIndex++] = b[j++];
        }
    }

    public static void main(String args[]) {
        int a[] = new int[]{-3, 5, INVALID_NUM, 7, INVALID_NUM, 10, INVALID_NUM, 11, INVALID_NUM};
        int b[] = new int[]{-1, 2, 6, 12};

        PrintUtil.println("Input a -> ", a);
        PrintUtil.println("Input b -> ", b);

        merge(a, b);
        PrintUtil.println("Output -> ", a);
    }
}
