package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

public class DistributeChocolate {

    public static void distribute(int[] m, int n, List<Integer> result) {
        if (m == null || m.length == 0 || n == 0) {
            return;
        }
        if (m.length < n) {
            throw new IllegalArgumentException("");
        }

        sortPackets(m, 0, m.length - 1);
        findResult(m, n, result);
    }

    private static void findResult(int[] m, int n, List<Integer> result) {
        int minimum = Integer.MAX_VALUE;
        int start = -1;

        for (int i = n - 1; i < m.length; i++) {
            int diff = m[i] - m[i - (n - 1)];
            if (diff < minimum) {
                minimum = diff;
                start = i - (n - 1);
            }
        }
        for (int i = start; i < start + n; i++) {
            result.add(m[i]);
        }
    }

    // Quick sort
    private static void sortPackets(int[] m, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = partition(m, start, end);

        sortPackets(m, start, pivot - 1);
        sortPackets(m, pivot + 1, end);
    }

    private static int partition(int[] m, int start, int end) {

        int pivot = m[end];

        int firstLargerIndex = start;
        int current = start;

        for (; current <= end - 1; current++) {
            if (m[current] < pivot) {
                ArrayUtil.swap(m, current, firstLargerIndex++);
            }
        }

        if (m[firstLargerIndex] != pivot) {
            ArrayUtil.swap(m, firstLargerIndex, end);
        }

        return firstLargerIndex;
    }

    public static void main(String args[]) {
        int m[] = new int[]{12, 4, 7, 9, 2, 23, 25, 41, 30, 40, 28, 42, 30, 44, 48, 43, 50};
        List<Integer> result = new ArrayList<>();
        distribute(m, 7, result);

        System.out.println(result);
    }
}
