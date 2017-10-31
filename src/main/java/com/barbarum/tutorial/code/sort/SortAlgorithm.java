package com.barbarum.tutorial.code.sort;

import com.barbarum.tutorial.code.Helper;

public class SortAlgorithm {

    public static void quickSort(int[] data, int start, int end) {
        if (data == null || data.length <= 1 || start >= end) {
            return;
        }

        int forward = start;
        int backward = end;

        int pivot = data[end];

        while (forward <= backward) {
            while (data[forward] < pivot) {
                forward++;
            }
            while (data[backward] > pivot) {
                backward--;
            }
            if (forward <= backward) {
                Helper.swap(data, forward, backward);
                forward++;
                backward--;
            }
        }

        if (start < backward) {
            quickSort(data, start, backward);
        }
        if (end > forward) {
            quickSort(data, forward, end);
        }

    }
}
