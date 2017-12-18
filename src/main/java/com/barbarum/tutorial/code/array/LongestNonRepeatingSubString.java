package com.barbarum.tutorial.code.array;

public class LongestNonRepeatingSubString {

    public static String findLongestNonRepeatingSubString(String string) {
        if (string == null || string.length() <= 1) {
            return string;
        }

        int charTable[] = initialTraverseTable();

        int maxDistance = 1;
        int index = 1;

        for (int start = 0, end = 1; end < string.length(); end++) {
            int previousSeen = charTable[string.charAt(end)];

            if (previousSeen != -1) {
                start = Math.max(start, previousSeen + 1);
                int distance = end - start + 1;
                if (distance > maxDistance) {
                    maxDistance = distance;
                    index = end + 1;
                }
            }

            charTable[string.charAt(end)] = end;
        }

        return string.substring(index - maxDistance, index);
    }

    private static int[] initialTraverseTable() {
        int table[] = new int[256];
        for (int i = 0; i < table.length; i++) {
            table[i] = -1;
        }
        return table;
    }

    public static void main(String args[]) {
        System.out.println(findLongestNonRepeatingSubString("ABCDABDEFGCABD"));
    }

}
