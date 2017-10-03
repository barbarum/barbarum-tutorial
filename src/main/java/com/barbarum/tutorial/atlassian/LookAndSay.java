package com.barbarum.tutorial.atlassian;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LookAndSay {
    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        List<String> input = readList(new Scanner(System.in));

        StringBuilder start = new StringBuilder();

        for (int i = 0; i < input.size() - 1; i++) {
            start.append(input.get(i));
        }

        System.out.println(LookAndSay(start.toString(), Integer.parseInt(input.get(input.size() - 1))));
    }

    public static String LookAndSay(String num) {
        if (num.isEmpty()) {
            return num;
        }

        String data = "" + num;
        if (data.length() == 1) {
            return "" + 1 + data;
        }
        int start = 0, index = 1;

        StringBuilder builder = new StringBuilder();

        for (; index < data.length(); index++) {
            if (data.charAt(index) != data.charAt(index - 1)) {
                builder.append(index - start).append(data.charAt(start));
                start = index;
            }
        }
        builder.append(index - start).append(data.charAt(start));

        return builder.toString();
    }


    public static String LookAndSay(String start, int num) {
        if (num <= 0) {
            return "";
        }
        String data = start;
        while ((num--) > 0) {
            data = LookAndSay(data);
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
