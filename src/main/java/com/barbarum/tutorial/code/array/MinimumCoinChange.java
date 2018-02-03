package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

import java.util.Arrays;

public class MinimumCoinChange {

    public static int findMinimumCoinToChange(int[] coins, int target) {
        if (coins == null || coins.length == 0 || target < 0) {
            return -1;
        }
        if (target == 0) {
            return 0;
        }
        if (coins.length == 1) {
            return coins[0] == target ? 1 : -1;
        }

        int[] cache = new int[target + 1];
        initialize(cache);

        cache[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= target; i++) {
                int previous = cache[i - coin];
                if (previous == -1) {
                    continue;
                }
                cache[i] = cache[i] == -1 ? 1 + previous : Math.min(cache[i], 1 + previous);
            }
        }

        return cache[target];
    }

    private static void initialize(int[] cache) {
        Arrays.fill(cache, -1);
    }

    public static void main(String args[]) {
        PrintUtil.println("Output->", findMinimumCoinToChange(new int[]{2, 5, 3}, 7));
    }
}
