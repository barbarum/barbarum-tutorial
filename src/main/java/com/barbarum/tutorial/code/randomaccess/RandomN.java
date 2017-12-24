package com.barbarum.tutorial.code.randomaccess;

import com.barbarum.tutorial.util.AssertUtil;

import java.util.Random;

public class RandomN {

    private static int rand2() {
        return new Random().nextInt(2);
    }

    public static int randN(int n) {
        if (n <= 1) {
            throw new IllegalArgumentException();
        }

        if (n == 2) {
            return rand2();
        }

        int mode = (n + 1) / 2;
        int result = mode * rand2() + randN(mode);

        return result != n ? result : randN(n);
    }

    public static void main(String args[]) {
        AssertUtil.execute(19, 1 << 16, RandomN::randN);
    }
}
