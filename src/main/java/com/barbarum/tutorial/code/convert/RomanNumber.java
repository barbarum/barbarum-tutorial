package com.barbarum.tutorial.code.convert;

/**
 *
 */
public class RomanNumber {

    public static String intToRoman(int input) {

        int num = input;

        char[][] template = new char[4][3];
        template[0] = new char[]{'I', 'V', 'X'};
        template[1] = new char[]{'X', 'L', 'C'};
        template[2] = new char[]{'C', 'D', 'M'};
        template[3] = new char[]{'M', ' ', ' '};

        StringBuilder builder = new StringBuilder();
        int high = 0;

        while (num != 0) {
            builder.insert(0, convert(num % 10, template[high++]));
            num = num / 10;
        }

        return builder.toString();
    }


    private static String convert(int base, char[] template) {
        int high = base / 5;
        int temp = base % 5;

        if (temp == 0) {
            return "" + (high == 1 ? template[1] : "");
        }

        if (temp == 4) {
            return "" + template[0] + (high == 0 ? template[1] : template[2]);
        }
        StringBuilder builder = new StringBuilder();
        if (high == 1) {
            builder.append(template[1]);
        }
        for (int i = 0; i < temp; i++) {
            builder.append(template[0]);
        }
        return builder.toString();
    }
}
