package com.barbarum.tutorial.code.string;

public class PrintMaximumNumberOfAByUsingGivenKeys {


    public static int findMaximum(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n <= 6) {
            return n;
        }

        int cache[] = new int[n + 1];
        cache[0] = 0;

        for (int i = 1; i <= 6; i++) {
            cache[i] = i;
        }

        for (int i = 7; i <= n; i++) {
            cache[i] = i;
            for (int j = i - 3; j >= 1; j--) {
                cache[i] = Math.max(cache[i], cache[j] * (i - j - 1));
            }
        }

        return cache[n];
    }

    public static void main(String args[]) {
        for (int i = 0; i <= 20; i++) {
            System.out.println(String.format("Print maximum number of A for %s: %s", i, findMaximum(i)));
        }
    }
}
