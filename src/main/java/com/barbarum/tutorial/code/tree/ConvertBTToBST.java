package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

import java.util.LinkedList;
import java.util.List;

public class ConvertBTToBST {

    public static void convert(Node<Integer> root) {
        LinkedList<Integer> cache = new LinkedList<>();

        inOrderTraversal(root, cache);
        useOneSortAlgorithm(cache);
        inOrderTraversalSet(root, cache);
    }

    private static void inOrderTraversal(Node<Integer> root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.getLeft(), result);
        result.add(root.getData());
        inOrderTraversal(root.getRight(), result);
    }

    private static void inOrderTraversalSet(Node<Integer> root, LinkedList<Integer> result) {
        if (root == null) {
            return;
        }
        inOrderTraversalSet(root.getLeft(), result);
        root.setData(result.poll());
        inOrderTraversalSet(root.getRight(), result);
    }

    // use any sort algorithm which would take O(nlogn) to solve this issue. And also mind using stable ones.
    // But in this case, we try to familiar with quick sort.
    private static void useOneSortAlgorithm(LinkedList<Integer> cache) {
        quickSort(cache, 0, cache.size() - 1);
    }

    private static void quickSort(LinkedList<Integer> list, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivotIndex = partition(list, start, end);
        quickSort(list, start, pivotIndex - 1);
        quickSort(list, pivotIndex + 1, end);
    }

    private static int partition(LinkedList<Integer> list, int start, int end) {
        int pivotIndex = findPivotIndex(list, start, end);
        swap(list, pivotIndex, end);

        int pivot = list.get(end);
        int result = start;

        for (int i = start; i <= end - 1; i++) {
            if (list.get(i) < pivot) {
                swap(list, i, result);
                result++;
            }
        }

        swap(list, end, result);

        return result;
    }

    private static int findPivotIndex(LinkedList<Integer> list, int start, int end) {
        return end;
    }

    private static void swap(LinkedList<Integer> list, int origin, int dest) {
        if (origin == dest) {
            return;
        }
        Integer temp = list.get(origin);
        list.set(origin, list.get(dest));
        list.set(dest, temp);
    }
}
