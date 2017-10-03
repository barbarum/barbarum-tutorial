package com.barbarum.tutorial.code;

import java.util.*;

public class ThreeSum {

    public static List<List<Integer>> effectiveThreeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        for (int end = nums.length - 1; end >= 2 && nums[end] >= 0; end--) {

            //Filter duplication
            if (end < nums.length - 1 && nums[end] == nums[end + 1]) {
                continue;
            }

            // Two sum && filter duplication
            int target = 0 - nums[end], first = 0, last = end - 1;
            while (first < last) {
                if (nums[first] + nums[last] < target) {
                    first++;
                } else if (nums[first] + nums[last] > target) {
                    last--;
                } else {
                    result.add(Arrays.asList(nums[first], nums[last], nums[end]));
                    first++;
                    last--;

                    //Filter duplication
                    while (first <= last && nums[first] == nums[first - 1]) {
                        first++;
                    }
                    while (last >= first && nums[last] == nums[last + 1]) {
                        last--;
                    }

                }
            }
        }

        return result;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = nums.length - 1; i >= 2 && nums[i] >= 0; i--) {
            // Mind the duplicated number. Need to allow the duplicated number to execute once before filtering.
            if (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                continue;
            }
            result.addAll(twoSum(nums, i, 0 - nums[i]));
        }

        return result;
    }

    private static List<List<Integer>> twoSum(int[] nums, int end, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        while (--end >= 0) {
            if (map.containsKey(nums[end])) {
                result.add(new ArrayList<>(Arrays.asList(nums[end], map.get(nums[end]), -target)));

                //Filtering duplication
                while (end >= 1 && nums[end] == nums[end - 1]) {
                    end--;
                }

            } else {
                map.put(target - nums[end], nums[end]);
            }
        }
        return result;
    }
}
