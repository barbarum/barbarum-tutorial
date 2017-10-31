package com.barbarum.tutorial.code.convert;

public class PalindromeNumber {

    public static boolean isPalindrome(int input) {
        int num = input;

        if (num < 0 || (num != 0 && num % 10 == 0)) {
            return false;
        }
        if (num < 10) {
            return true;
        }
        int reverse = 0;
        while (reverse < num) {
            reverse = reverse * 10 + num % 10;
            num = num / 10;
        }
        return reverse == num || (reverse / 10 == num);
    }
}
