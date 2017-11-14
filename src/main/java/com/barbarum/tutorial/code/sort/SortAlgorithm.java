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
        if (start >= end) {
            // sorted;
            return;
        }

        int p = partition(data, start, end);

        quickSort(data, start, p - 1);
        quickSort(data, p + 1, end);
    }

    /**
     * Lomuto partition scheme
     * <p>
     * 1. Pick the last element of an array as pivot.
     * 2. Loop to find elements less than pivot, and compact them into the left side of the array.
     * 3. The last position of the compact index stays are the position for pivot element. We swap this element if it's less than the element on the position.
     */
    private static int partition(int[] data, int start, int end) {

        int pivot = findPivot(data, start, end);

        int index = start;
        int current = start;

        for (; current <= end - 1; current++) {
            if (data[current] < pivot) {
                ArrayUtil.swap(data, index, current);
                index++;
            }
        }

        if (data[index] != pivot) {
            ArrayUtil.swap(data, index, end);
        }

        return index;
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
        mergeSort(data, 0, data.length - 1, Arrays.copyOf(data, data.length));
    }

    private static void mergeSort(int[] origin, int start, int end, int[] workingCopy) {
        if (start == end) {
            return;
        }

        int median = (start + end) / 2;

        mergeSort(workingCopy, start, median, origin);
        mergeSort(workingCopy, median + 1, end, origin);

        mergeSortConquer(origin, start, median, end, workingCopy);
    }

    private static void mergeSortConquer(int[] dest, int start, int median, int end, int[] src) {
        int left = start;
        int right = median + 1;

        for (int i = start; i <= end; i++) {
            if (left <= median && (right > end || src[left] <= src[right])) {
                dest[i] = src[left++];
            } else if ((right <= end) && (left > median || src[right] < src[left])) {
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
