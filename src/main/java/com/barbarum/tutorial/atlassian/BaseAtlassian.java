package com.barbarum.tutorial.atlassian;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BaseAtlassian {

    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        List<Integer> input = readList(new Scanner(System.in));

        long num = 0;

        for (int i = 0; i < input.size(); i++) {
            num = num * 10 + input.get(i);
        }

        System.out.println(convert(num));
    }

    private static String convert(long input) {
        char[] base = new char[]{'0', 'a', 't', 'l', 's', 'i', 'n'};

        StringBuilder builder = new StringBuilder();
        long temp = input;

        while (temp > 0) {
            builder.append(base[(int) (temp % 7)]);
            temp = temp / 7;
        }

        return builder.reverse().toString();
    }

    private static List<Integer> readList(Scanner scanner) {

        List<Integer> result = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if (data == null || data.isEmpty()) {
                break;
            }
            result.add(Integer.parseInt(data));
        }

        return result;
    }
}
