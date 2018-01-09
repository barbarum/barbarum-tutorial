package com.barbarum.tutorial.code.array;

public class FindMedianOfSortedArraysV2 {


    public static double findMedianSortedArrays(int[] a, int[] b) {
        int aLen = a.length;
        int bLen = b.length;

        if ((aLen + bLen) % 2 != 0) {
            return findKth(a, b, (aLen + bLen) / 2, 0, aLen - 1, 0, bLen - 1);
        }

        return (findKth(a, b, (aLen + bLen) / 2, 0, aLen - 1, 0, bLen - 1)
                + findKth(a, b, (aLen + bLen) / 2 - 1, 0, aLen - 1, 0, bLen - 1)) * 0.5;
    }

    private static int findKth(int[] a, int[] b, int k, int aStart, int aEnd, int bStart, int bEnd) {
        int aLen = aEnd - aStart + 1;
        int bLen = bEnd - bStart + 1;

        if (aLen == 0) {
            return b[bStart + k];
        }
        if (bLen == 0) {
            return a[aStart + k];
        }
        if (k == 0) {
            return Math.min(a[aStart], b[bStart]);
        }

        return binarySearch(a, b, k, aStart, aEnd, bStart, bEnd, aLen, bLen);
    }

    private static int binarySearch(int[] a, int[] b, int k, int aStart, int aEnd, int bStart, int bEnd, int aLen, int bLen) {
        int aMidOffset = aLen * k / (aLen + bLen);
        int bMidOffset = k - aMidOffset - 1;

        int aMid = aStart + aMidOffset;
        int bMid = bStart + bMidOffset;

        if (a[aMid] > b[bMid]) {
            return findKth(a, b, k - bMidOffset - 1, aStart, aMid, bMid + 1, bEnd);
        } else {
            return findKth(a, b, k - aMidOffset - 1, aMid + 1, aEnd, bStart, bMid);
        }
    }
}
