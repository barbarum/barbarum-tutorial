package com.barbarum.tutorial.code;

public class PalindromeNumber {

    public static boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        int reverse = 0;
        while (reverse < x) {
            reverse = reverse * 10 + x % 10;
            x = x / 10;
        }
        return reverse == x || (reverse / 10 == x);
    }
}
