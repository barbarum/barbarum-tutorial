package com.barbarum.tutorial.code.string;

import static java.util.Arrays.fill;

public class FirstNonRepeatingCharacter {

    public static Character findFirst(String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        if (string.length() == 1) {
            return string.charAt(0);
        }

        int lastSeen[] = new int[26];
        fill(lastSeen, -1);
        char characters[] = string.toCharArray();

        for (int i = 0; i < characters.length; i++) {
            int lastIndex = lastSeen[characters[i] - 'A'];
            if (lastIndex != -1) {
                characters[lastIndex] = ' ';
            }
            lastSeen[characters[i] - 'A'] = i;
        }

        for (int i = 0; i < characters.length; i++) {
            if (characters[i] != ' ') {
                return characters[i];
            }
        }

        return null;
    }

    public static void main(String args[]) {
        System.out.println(findFirst("ADBCGHIEFKJLADTVDERFSWVGHQWCNOPENSMSJWIERTFB"));
    }
}
