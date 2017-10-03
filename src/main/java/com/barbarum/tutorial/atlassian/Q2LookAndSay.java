package com.barbarum.tutorial.atlassian;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q2LookAndSay {
    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        List<String> input = readList(new Scanner(System.in));

        StringBuilder start = new StringBuilder();

        for (int i = 0; i < input.size() - 1; i++) {
            start.append(input.get(i));
        }

        System.out.println(lookAndSay(start.toString(), Integer.parseInt(input.get(input.size() - 1))));
    }

    public static String lookAndSay(String num) {
        if (num.isEmpty()) {
            return num;
        }
        if (num.length() == 1) {
            return "" + 1 + num;
        }

        int start = 0;
        int index = 1;

        StringBuilder builder = new StringBuilder();

        for (; index < num.length(); index++) {
            if (num.charAt(index) != num.charAt(index - 1)) {
                builder.append(index - start).append(num.charAt(start));
                start = index;
            }
        }
        builder.append(index - start).append(num.charAt(start));

        return builder.toString();
    }


    public static String lookAndSay(String start, int num) {
        if (num <= 0) {
            return "";
        }
        String data = start;
        while ((num--) > 0) {
            data = lookAndSay(data);
        }
        return data;
    }

    private static List<String> readList(Scanner scanner) {

        List<String> result = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if (data == null || data.isEmpty()) {
                break;
            }
            result.add(data);
        }

        return result;
    }
}
