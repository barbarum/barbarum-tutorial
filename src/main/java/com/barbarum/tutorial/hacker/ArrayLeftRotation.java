package com.barbarum.tutorial.hacker;

public class ArrayLeftRotation {

    public static void main(String[] args) {
        int[] rotation = doLeftRotation(new int[]{0, 1, 2, 3, 4}, 12);

        StringBuilder builder = new StringBuilder();

        for (int item : rotation) {
            builder.append(item).append(" ");
        }

        System.out.println(builder.toString().trim());
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
}
