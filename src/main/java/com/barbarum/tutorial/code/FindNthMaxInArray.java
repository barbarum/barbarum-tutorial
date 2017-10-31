package com.barbarum.tutorial.code;

import java.util.Arrays;

public class FindNthMaxInArray {

    public int findNthMax(int[] data, int n) {
        if (data == null || data.length == 0 || n < 1) {
            return -1;
        }
        return quickSelect(data, data.length - n, 0, data.length - 1);
    }

    public int quickSelect(int[] data, int target, int start, int end) {
        int left = start;
        int right = end;
        int pivot = data[end];

        while (left != right) {
            while (data[left] < pivot && left < right) {
                left++;
            }
            while (data[right] >= pivot && right > left) {
                right--;
            }
            if (left == right) {
                break;
            }
            swap(data, left, right);
        }

        if (target == left) {
            return data[target];
        }

        if (target < left) {
            return quickSelect(data, target, start, left - 1);
        }

        return quickSelect(data, target, right + 1, end);
    }

    public void quickSort(int[] data, int start, int end) {
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
                swap(data, forward, backward);
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

    public void swap(int[] data, int left, int right) {
        int temp = data[left];
        data[left] = data[right];
        data[right] = temp;
    }

    public static void main(String[] args) {
        int data[] = Arrays
                .stream("9 2 4 7 3 7 10".split(" ", -1))
                .mapToInt(Integer::parseInt)
                .toArray();
        new FindNthMaxInArray().quickSort(data, 0, data.length - 1);

        System.out.println(Arrays.toString(data));


        System.out.println(new FindNthMaxInArray().findNthMax(data, 3));
    }
}
