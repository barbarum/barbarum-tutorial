package com.barbarum.tutorial.code.string;

public class BalancedParentheses {

    public static boolean validate(String string) {
        if (string == null || string.length() == 0) {
            return true;
        }

        int front = 0;
        int back = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(') {
                front++;
            } else if (string.charAt(i) == ')') {
                back++;

                if (back > front) {
                    return false;
                }
            }
        }

        return front == back;
    }

    public static void main(String args[]) {
        System.out.println(validate("((BCD)AE)"));
        System.out.println(validate(")(PH)N(X)"));
    }
}
