package com.barbarum.tutorial.code;

public class RegularExpressionMatching {

    public static boolean isMatch(String origin, String p) {

        String s = origin;

        if (p.isEmpty()) return s.isEmpty();

        if (p.length() == 1) {
            return s.length() == 1 && ((s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
        }

        if (p.charAt(1) != '*') {
            return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
        }

        while (!s.isEmpty()) {
            // Match p without * from the start of s.
            if (isMatch(s, p.substring(2))) {
                return true;
            }
            // Since p without * is not matched, then s[0] must match p[0] or p[0] is ".".
            if (!(s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
                return false;
            }
            s = s.substring(1);
        }

        return isMatch(s, p.substring(2));
    }
}
