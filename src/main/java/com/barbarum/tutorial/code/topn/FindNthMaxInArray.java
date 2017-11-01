package com.barbarum.tutorial.code.topn;

import com.barbarum.tutorial.helper.Helper;
import com.barbarum.tutorial.code.sort.SortAlgorithm;

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
            Helper.swap(data, left, right);
        }

        if (target == left) {
            return data[target];
        }

        if (target < left) {
            return quickSelect(data, target, start, left - 1);
        }

        return quickSelect(data, target, right + 1, end);
    }

    public static void main(String[] args) {
        int data[] = Arrays
                .stream("9 2 4 7 3 7 10".split(" ", -1))
                .mapToInt(Integer::parseInt)
                .toArray();
        SortAlgorithm.quickSort(data, 0, data.length - 1);

        System.out.println(Arrays.toString(data));


        System.out.println(new FindNthMaxInArray().findNthMax(data, 3));
    }
}
