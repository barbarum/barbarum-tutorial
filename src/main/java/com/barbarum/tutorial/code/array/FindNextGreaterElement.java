package com.barbarum.tutorial.code.array;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FindNextGreaterElement {

    /**
     * Given an array of integers(positive or negative), vertical the next greater element of all elements in the array. If there is no greater element then vertical null.
     * Next greater element of an array element array[i], is an integer array[j], such that
     * 1. array[i] < array[j]
     * 2. i < j
     * 3. j - i is minimum
     * i.e. array[j] is the first element on the right of array[i] which is greater than array[i].
     * For example:
     * Input array:  98, 23, 54, 12, 20, 7, 27
     * Output:
     * Next greater element for 23     = 54
     * Next greater element for 12     = 20
     * Next greater element for 7     = 27
     * Next greater element for 20     = 27
     * Next greater element for 27     = null
     * Next greater element for 54     = null
     * Next greater element for 98     = null
     *
     * @param nums
     */

    // Stack!!!
    public static Map<Integer, Integer> findNextGreaterElement(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        Map<Integer, Integer> result = new HashMap<>();

        // traverse and find greater element pair
        traverse(nums, stack, result);

        while (!stack.isEmpty()) {
            result.put(stack.pop(), null);
        }

        return result;
    }

    private static void traverse(int[] nums, Deque<Integer> stack, Map<Integer, Integer> result) {
        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() < num) {
                result.put(stack.pop(), num);
            }
            stack.push(num);
        }
    }


    public static void main(String args[]) {
        System.out.println(findNextGreaterElement(new int[]{98, 23, 54, 12, 20, 7, 27}));
    }
}
