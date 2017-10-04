package com.barbarum.tutorial.hacker.atlassian;

import java.util.Scanner;

import static com.barbarum.tutorial.hacker.HackerUtil.*;

public class RobotArm {

    public static void main(String[] args) {
        String command = readLine(new Scanner(System.in));
        System.out.println(print(command));
    }

    public static String print(String command) {
        int[] result = new int[10];

        int index = 0;
        int start = 0;
        boolean isPicked = false;

        while (index < command.length()) {
            char c = command.charAt(index++);
            if (c == 'P') {
                start = 0;
                isPicked = true;
            } else if (c == 'M') {
                start++;
            } else if (c == 'L') {
                if (!isPicked) {
                    continue;
                }
                result[start]++;
                isPicked = false;
                if (result[start] == 16) {
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IllegalArgumentException();
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int item : result) {
            builder.append(Integer.toHexString(item).toUpperCase());
        }

        return builder.toString();
    }
}
