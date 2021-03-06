package com.barbarum.tutorial.code.week35;

/**
 * https://leetcode.com/contest/leetcode-weekly-contest-53/problems/binary-number-with-alternating-bits/
 * <p>
 * 101010101
 * 001010101
 * <p>
 * 1000000000
 * 0111111111
 */
public class BinaryNumberWithAlternatingBits {

    public static void main(String[] args) {

        System.out.println(Integer.toBinaryString(5) + ":" + BinaryNumberWithAlternatingBits.hasAlternatingBits(5));
        System.out.println(Integer.toBinaryString(7) + ":" + BinaryNumberWithAlternatingBits.hasAlternatingBits(7));
        System.out.println(Integer.toBinaryString(-1) + ":" + BinaryNumberWithAlternatingBits.hasAlternatingBits(-1));
    }

    public static boolean hasAlternatingBits(int m) {
        int n = m;
        return ((n ^= (n >>> 2)) & (n - 1)) == 0;
    }
}
