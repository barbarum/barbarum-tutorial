package com.barbarum.tutorial.code.string;

public class CountDistinctBinaryStrings {

    public static int count(int n) {
        if (n == 0) {
            return 1;
        }

        int n_2 = 1;
        int n_1 = 2;
        int result = n_1;

        for (int i = 2; i <= n; i++, n_2 = n_1, n_1 = result) {
            result = n_1 + n_2;
        }

        return result;
    }

    public static void main(String args[]) {

        for (int i = 0; i <= 20; i++) {
            System.out.println(String.format("Count %s:%s", i, count(i)));
        }
    }
}
