package com.barbarum.tutorial.code.string;

public class CountDistinctBinaryStrings {

    public static int count(int n) {
        if (n == 0) {
            return 1;
        }

        int cache[] = new int[n + 1]; // array can be replaced to only store last two caches.
        cache[0] = 1;
        cache[1] = 2;

        for (int i = 2; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }

        return cache[n];
    }

    public static void main(String args[]) {

        for (int i = 0; i <= 20; i++) {
            System.out.println(String.format("Count %s:%s", i, count(i)));
        }
    }
}
