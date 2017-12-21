package com.barbarum.tutorial.code.string;

public class LongestCommonSubSequences {

    public static int findLongestCommonSubSequences(String a, String b) { // 'axu', 'bauex'
        if (a == null || a.length() == 0 || b == null || b.length() == 0) {
            return 0;
        }

        int cache[][] = new int[a.length() + 1][b.length() + 1];// [4][6]
        fillLongestCommonSubSequences(a, b, cache);
        return cache[a.length()][b.length()];
    }

    private static void fillLongestCommonSubSequences(String a, String b, int[][] cache) {
        for (int i = 0; i <= a.length(); i++) {
            cache[i][0] = 0;
        }
        for (int j = 0; j <= b.length(); j++) {
            cache[0][j] = 0;
        }

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    cache[i][j] = cache[i - 1][j - 1] + 1;
                } else {
                    cache[i][j] = Math.max(cache[i][j - 1], cache[i - 1][j]);
                }
            }
        }
    }

    public static void main(String args[]) {
        System.out.println(findLongestCommonSubSequences("ACBEA", "ADCA"));
        System.out.println(findLongestCommonSubSequences("axu", "bauex"));
    }
}
