package com.barbarum.tutorial.code;

public class RomanToIntegerNumber {

    public static int romanToInt(String s) {
        if (s == null || s.isEmpty()) return 0;

        int[] romans = new int[]{'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] nums = new int[]{1, 5, 10, 50, 100, 500, 1000};

        int[] combo = new int[26];
        for (int i = 0; i < romans.length; i++) {
            combo[romans[i] - 'A'] = nums[i];
        }

        int result = 0;
        char previous = s.charAt(0);

        while (!s.isEmpty()) {
            if (combo[previous - 'A'] < combo[s.charAt(0) - 'A']) {
                result -= combo[previous - 'A'] * 2;
            }
            result += combo[s.charAt(0) - 'A'];
            previous = s.charAt(0);
            s = s.substring(1);
        }
        return result;
    }

}
