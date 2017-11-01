package com.barbarum.tutorial.code.subset;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

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
        execute(ExtendedRandom::rand7Java);

        System.out.println("Stackoverflow -> ");
        execute(ExtendedRandom::rand7Stackoverflow);
    }

    public static void execute(Supplier<Integer> supplier) {
        long start = System.currentTimeMillis();

        int hit[] = new int[8];
        long loop = 1L << 24;
        long num = loop;

        while ((num--) > 0) {
            hit[supplier.get()]++;
        }

        long end = System.currentTimeMillis();

        System.out.println(Arrays.toString(Arrays
                .stream(hit)
                .skip(1)
                .mapToDouble(item -> item / (loop * 1.0) * 100)
                .toArray()));

        System.out.println("Execution Time: " + (end - start) + "ms");
    }
}
