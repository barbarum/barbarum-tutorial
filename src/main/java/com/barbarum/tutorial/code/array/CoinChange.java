package com.barbarum.tutorial.code.array;

public class CoinChange {

    public static int makeChanges(int coins[], int n) {

        int[] table = new int[n + 1];

        // Base case (If given value is 0)
        table[0] = 1;

        // Pick all coins one by one and update the table[]
        // values after the index greater than or equal to
        // the value of the picked coin
        for (int coin : coins) {
            for (int j = coin; j <= n; j++) {
                table[j] += table[j - coin];
            }
        }
        return table[n];
    }


    public static void main(String args[]) {
        System.out.println(makeChanges(new int[]{20, 10, 5, 1}, 20));
    }
}
