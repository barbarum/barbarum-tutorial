package com.barbarum.tutorial.code.number;

import com.barbarum.tutorial.code.sort.SelectionAlgorithm;
import com.barbarum.tutorial.code.sort.SortAlgorithm;

import java.util.Arrays;

public class FindNthMaxInArray {

    public int findNthMax(int[] data, int n) {
        if (data == null || data.length == 0 || n < 1) {
            return -1;
        }
        return SelectionAlgorithm.quickSelect(data, n);
    }

    public static void main(String[] args) {
        int data[] = Arrays
                .stream("9 2 4 7 3 7 10".split(" ", -1))
                .mapToInt(Integer::parseInt)
                .toArray();


        SortAlgorithm.quickSort(data);
        System.out.println(Arrays.toString(data));
        
        System.out.println(new FindNthMaxInArray().findNthMax(data, 3));
    }
}
