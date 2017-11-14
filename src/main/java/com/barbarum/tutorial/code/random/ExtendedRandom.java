package com.barbarum.tutorial.code.random;

import com.barbarum.tutorial.util.AssertUtil;

import java.util.Random;

public class ExtendedRandom {

    public static int rand5() {
        return new Random().nextInt(5) + 1;
    }

    public static int rand7Java() {
        return new Random().nextInt(7) + 1;
    }

    public static int rand7Stackoverflow() {
        int i;
        do {
            i = 5 * (rand5() - 1) + rand5();  // i is now uniformly random between 1 and 25
        } while (i > 21);
        // i is now uniformly random between 1 and 21
        return i % 7 + 1;  // result is now uniformly random between 1 and 7
    }


    public static void main(String[] args) {
        System.out.println("Java -> ");
        AssertUtil.execute(ExtendedRandom::rand7Java);

        System.out.println("Stackoverflow -> ");
        AssertUtil.execute(ExtendedRandom::rand7Stackoverflow);
    }

}
