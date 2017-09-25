package com.barbarum.tutorial.leetcode;

/**
 * https://leetcode.com/problems/reverse-integer/description/

 Reverse digits of an integer.

 Example1: x = 123, return 321
 Example2: x = -123, return -321

 click to show spoilers.

 Note:
 The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.

 */
public class ReverseInteger {


    public static int reverseWithNum(int x) {
        long result = 0;
        int num = x;
        while (num != 0) {
            int delta = num % 10;
            num = num / 10;
            result = result * 10 + delta;

            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int) result;
    }

    public static int reverse(int x) {
        char[] array = ("" + x).toCharArray();
        long result = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == '-') {
                result = 0 - result;
                break;
            }
            result = result * 10 + (array[i] - '0');
        }

        return result <= Integer.MAX_VALUE && result >= Integer.MIN_VALUE ? (int) result : 0;
    }

    public static void main(String[] args) {

        System.out.println("" + (-55 % 10));

        System.out.println("" + (('3' - '0')));
    }
}
