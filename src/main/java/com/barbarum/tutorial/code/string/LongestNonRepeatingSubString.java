package com.barbarum.tutorial.code.string;

public class LongestNonRepeatingSubString {

    public static String findLongestNonRepeatingSubString(String string) {
        if (string == null || string.length() <= 1) {
            return string;
        }

        int lastSeenTable[] = initialTraverseTable();
        int maxDistance = 1;
        int index = 0;

        for (int start = 0, end = 0; end < string.length(); end++) {
            int lastSeen = lastSeenTable[string.charAt(end)];
            start = lastSeen == -1 || lastSeen < start ? start : lastSeen + 1;
            int distance = end - start + 1;

            if (distance > maxDistance) {
                maxDistance = distance;
                index = start;
            }

            lastSeenTable[string.charAt(end)] = end;
        }

        return string.substring(index, index + maxDistance);
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
        System.out.println(findLongestNonRepeatingSubString("ABCDEFGHIJKLM"));
    }

}
