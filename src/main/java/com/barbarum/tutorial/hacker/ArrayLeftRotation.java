package com.barbarum.tutorial.hacker;

import java.util.Scanner;

import static com.barbarum.tutorial.hacker.HackerUtil.*;

public class ArrayLeftRotation {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int length = readInt(scanner);
        int rotation = readInt(scanner);
        int[] input = readArray(scanner, length);

        int[] result = doLeftRotation(input, rotation);
        println(result);
    }


    public static int[] doLeftRotation(int[] input, int rotation) {
        if (input == null || input.length == 0) {
            return input;
        }

        int model = input.length;
        int absRotation = rotation % model;
        int[] result = new int[model];

        for (int i = 0; i < model; i++) {
            result[(i + model - absRotation) % model] = input[i];
        }

        return result;
    }

    private static void println(int[] rotation) {
        StringBuilder builder = new StringBuilder();
        for (int item : rotation) {
            builder.append(item).append(" ");
        }
        System.out.println(builder.toString().trim());
    }
}
