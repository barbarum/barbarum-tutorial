package com.barbarum.tutorial.code.sort;

import java.util.concurrent.ConcurrentHashMap;

import static com.barbarum.tutorial.util.InputUtil.swap;

public class SortPratices {

    public static void quickSort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }
        quickSort(data, 0, data.length - 1);
    }

    private static void quickSort(int[] data, int start, int end) {
        if (start >= end) {
            return;
        }

        int p = partition(data, start, end);

        quickSort(data, start, p - 1);
        quickSort(data, p + 1, end);
    }

    private static int partition(int[] data, int start, int end) {
        int pivotIndex = findPivotIndex(data, start, end);

        int pivot = data[pivotIndex];
        swap(data, pivotIndex, end);
        int result = start;

        for (int i = start; i < end; i++) {
            if (data[i] < pivot) {
                swap(data, result, i);
                result++;
            }
        }

        swap(data, result, end);
        return result;
    }

    private static int findPivotIndex(int[] data, int start, int end) {
        return end;
    }

    public static void mergeSort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }
        int[] workingCopy = new int[data.length];
        System.arraycopy(data, 0, workingCopy, 0, workingCopy.length);

        mergeSort(data, 0, data.length - 1, workingCopy);
    }

    private static void mergeSort(int[] data, int start, int end, int[] workingCopy) {
        if (start >= end) {
            return;
        }

        int mid = (start + end) / 2;

        mergeSort(workingCopy, start, mid, data);
        mergeSort(workingCopy, mid + 1, end, data);

        merge(data, start, mid, end, workingCopy);
    }

    private static void merge(int[] dest, int start, int mid, int end, int[] src) {
        int firstHalfIndex = start;
        int secondHalfIndex = mid + 1;
        int index = start;

        while (firstHalfIndex <= mid && secondHalfIndex <= end) {
            if (src[firstHalfIndex] <= src[secondHalfIndex]) {
                dest[index++] = src[firstHalfIndex++];
            } else {
                dest[index++] = src[secondHalfIndex++];
            }
        }
        while (firstHalfIndex <= mid) {
            dest[index++] = src[firstHalfIndex++];
        }
        while (secondHalfIndex <= end) {
            dest[index++] = src[secondHalfIndex++];
        }
    }

    private static int binarySearch(int[] data, int target) {
        if (data == null || data.length == 0) {
            return -1;
        }
        return binarySearch(data, 0, data.length - 1, target);
    }

    private static int binarySearch(int[] data, int start, int end, int target) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (data[mid] == target) {
            return mid;
        }
        return data[mid] > target
                ? binarySearch(data, start, mid - 1, target)
                : binarySearch(data, mid + 1, end, target);
    }

    public static void main(String args[]) {
        new ConcurrentHashMap<>(10);
    }
}
