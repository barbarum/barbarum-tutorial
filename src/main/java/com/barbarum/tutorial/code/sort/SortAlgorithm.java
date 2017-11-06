package com.barbarum.tutorial.code.sort;

import com.barbarum.tutorial.util.ArrayUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortAlgorithm {

    public static void quickSort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }

        quickSort(data, 0, data.length - 1);
    }

    private static void quickSort(int[] data, int start, int end) {
        if (start == end) {
            // sorted;
            return;
        }

        int pivot = findPivot(data, start, end);

        int forward = start;
        int backward = end;

        while (forward <= backward) {
            while (data[forward] < pivot) {
                forward++;
            }
            while (data[backward] > pivot) {
                backward--;
            }
            if (forward <= backward) {
                ArrayUtil.swap(data, forward, backward);
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

    private static int findPivot(int[] data, int start, int end) {
        return data[end];
    }

    /**
     * Heap sort algorithm
     * <p>
     * Time Complexity: Average - O(nlogn), Worst - O(nlogn)
     *
     * @param data the given data.
     */
    public static void heapSort(int[] data) {
        Collection<Integer> element = Arrays.stream(data)
                .boxed()
                .collect(Collectors.toList());

        // Using siftUp, which has the following time complexity guaranteed. The explanation refers to https://en.wikipedia.org/wiki/Binary_heap#Building_a_heap.
        // TC: Average - O(n), Worst - O(n)
        PriorityQueue<Integer> queue = new PriorityQueue<>(element);

        // TC: Average - O(nlogn), Worst - O(nlogn)
        IntStream.range(0, data.length)
                .forEach(index -> data[index] = queue.poll());
    }

    public static void mergeSort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }
        mergeSort(data, 0, data.length, Arrays.copyOf(data, data.length));
    }

    private static void mergeSort(int[] origin, int start, int end, int[] workingCopy) {
        if (start == end - 1) {
            return;
        }

        int median = (start + end) / 2;

        mergeSort(workingCopy, start, median, origin);
        mergeSort(workingCopy, median, end, origin);

        mergeSortConquer(origin, start, median, end, workingCopy);
    }

    private static void mergeSortConquer(int[] dest, int start, int median, int end, int[] src) {
        int left = start;
        int right = median;

        for (int i = start; i < end; i++) {
            if (left < median && (right == end || src[left] <= src[right])) {
                dest[i] = src[left++];
            } else if ((right < end) && (left == median || src[right] < src[left])) {
                dest[i] = src[right++];
            }
        }
    }


    public static void main(String[] args) {
        int[] elements = new int[]{14, 281, 20, 1, -5};
        heapSort(elements);
        System.out.println(Arrays.toString(elements));
    }
}
