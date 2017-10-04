package com.barbarum.tutorial.hacker;

import java.util.Scanner;

public class HackerUtil {

    public static int[] readArray(Scanner scanner, int length) {
        int[] input = new int[length];
        for (int i = 0; i < length; i++) {
            input[i] = scanner.nextInt();
        }
        return input;
    }

    public static String readLine(Scanner scanner) {
        return scanner.next();
    }

    public static int readInt(Scanner scanner) {
        return scanner.nextInt();
    }
}
