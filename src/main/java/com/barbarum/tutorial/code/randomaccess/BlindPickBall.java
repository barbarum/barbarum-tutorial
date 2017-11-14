package com.barbarum.tutorial.code.randomaccess;

import java.util.Random;

public class BlindPickBall {

    public static String pick() {
        String rule = "AAABBBBBCCDDDDE";

        StringBuilder builder = new StringBuilder();
        Random random = new Random();

        while (!rule.isEmpty()) {
            char c = rule.charAt(random.nextInt(rule.length()));
            builder.append(c);
            rule = rule.replaceAll(String.valueOf(c), "");
        }

        return builder.toString();
    }

    public static void main(String args[]) {
        System.out.println(pick());
    }
}
