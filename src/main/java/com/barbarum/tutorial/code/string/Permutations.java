package com.barbarum.tutorial.code.string;

import java.util.*;

public class Permutations {


    public static List<String> findAllPermutations(String string) {
        if (string == null || string.length() == 0) {
            return Collections.emptyList();
        }

        int length = string.length();
        LinkedList<String> queue = new LinkedList<>();

        queue.add(string.substring(0, 1));

        while (queue.peek().length() != length) {
            String subString = queue.poll();
            int subLength = subString.length();

            for (int i = 0; i < subLength; i++) {
                String prefix = subString.substring(0, i);
                String suffix = subString.substring(i, subLength);
                queue.add(prefix + string.charAt(subLength) + suffix);
            }

            queue.add(subString + string.charAt(subLength));
        }

        return queue;
    }

    public static void main(String args[]) {
        System.out.println(findAllPermutations("abc"));
    }
}
