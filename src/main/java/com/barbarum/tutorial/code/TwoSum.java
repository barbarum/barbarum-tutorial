package com.barbarum.tutorial.code;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {


    public static int[] sumWithBasicAlgorithm(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] sumWithDiff(int[] nums, int target) {
        int[] map = new int[16030];
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i] + 5;       //给了一个5的缓冲
            if (diff < 0) continue;                //不在范围内
            if (map[diff] > 0) {                   //在map数组中
                return new int[]{map[diff] - 1, i};
            }
            map[nums[i] + 5] = i + 1;              //放入map数组中
        }
        return null;
    }

    public static int[] sumWithMap(int[] nums, int target) {
        Map<Integer, Integer> diffMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (diffMap.containsKey(nums[i])) {
                return new int[]{diffMap.get(nums[i]), i};
            }
            diffMap.put(target - nums[i], i);
        }
        return null;
    }
}