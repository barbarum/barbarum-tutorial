package com.barbarum.tutorial.code.sort;

import com.barbarum.tutorial.util.ArrayUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    /**
     * Heap sort algorithm
     * <p>
     * Time Complexity: Average - O(nlogn), Worst - O(nlogn)
     *
     * @param data
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


    public static void main(String[] args) {
        int[] elements = new int[]{14, 281, 20, 1, -5};
        heapSort(elements);
        System.out.println(Arrays.toString(elements));
    }
}
