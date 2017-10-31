package com.barbarum.tutorial.hacker.tutorial.day0;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class RansomNote {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();

        String[] magazine = new String[m];
        for (int magazine_i = 0; magazine_i < m; magazine_i++) {
            magazine[magazine_i] = in.next();
        }
        String[] ransom = new String[n];
        for (int ransom_i = 0; ransom_i < n; ransom_i++) {
            ransom[ransom_i] = in.next();
        }

        boolean result = canMakeRansomNote(magazine, ransom);
        System.out.println(result ? "Yes" : "No");
    }

    private static boolean canMakeRansomNote(String[] magazine, String[] ransom) {
        if (ransom.length == 0) {
            return true;
        }
        if (magazine.length == 0) {
            return false;
        }

        Map<String, Integer> map = new HashMap<>();

        Stream.of(ransom).forEach((item) -> {
            map.put(item, map.getOrDefault(item, 0) + 1);
        });

        for (String item : magazine) {
            if (map.containsKey(item)) {
                Integer count = map.get(item);
                if (count == 1) {
                    if (map.size() == 1) {
                        return true;
                    }
                    map.remove(item);
                } else {
                    map.put(item, --count);
                }
            }
        }

        return map.isEmpty();
    }
}