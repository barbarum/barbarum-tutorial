package com.barbarum.tutorial.code;

public class NumberOfBits {

    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {
        int result = 0;
        int temp = n;

        while (temp != 0) {
            result += (temp & 1);
            temp = temp >>> 1;
        }
        return result;
    }

    public static void main(String args[]) {
        System.out.println(hammingWeight(11));
    }
}
