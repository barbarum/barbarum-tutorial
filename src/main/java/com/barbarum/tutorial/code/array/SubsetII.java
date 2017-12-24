package com.barbarum.tutorial.code.array;

import java.util.*;
import java.util.stream.Collectors;

public class SubsetII {

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        Queue<Deque<Integer>> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();

        result.add(new LinkedList<>());
        result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));

        addOneSubset(nums, queue);

        while (!queue.isEmpty()) {
            Deque<Integer> current = queue.poll();
            if (isValid(current, nums)) {
                addIntIntoResult(nums, result, current);
            }
            for (int i = current.getLast() + 1; i < nums.length; i++) {
                LinkedList<Integer> item = new LinkedList<>(current);
                item.add(i);
                queue.offer(item);

                // Remove Duplication
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }

        return result;
    }

    private static void addIntIntoResult(int[] nums, List<List<Integer>> result, Deque<Integer> current) {
        if (current.isEmpty()) {
            result.add(Collections.emptyList());
            return;
        }

        List<Integer> list = current.stream()
                .map(item -> nums[item])
                .collect(Collectors.toList());

        result.add(list);
    }

    private static boolean isValid(Deque<Integer> current, int[] nums) {
        return !current.isEmpty() && current.getLast() < nums.length && current.size() < nums.length;
    }

    private static void addOneSubset(int[] nums, Queue<Deque<Integer>> queue) {
        for (int i = 0; i < nums.length; i++) {
            LinkedList<Integer> item = new LinkedList<>();
            item.add(i);
            queue.offer(item);

            // Remove Duplication
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
    }

    public static void main(String args[]) {
        System.out.println(subsetsWithDup(new int[]{1, 2, 2}));
    }
}
