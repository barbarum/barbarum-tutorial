package com.barbarum.tutorial.code;

import java.util.List;
import java.util.Map;

public class RegularExpressionMatching {

//    public static boolean isMatch(String s, String p) {
//        List<String> group = new ArrayList<>();
//        Map<Integer, Boolean> regexpFlags = new HashMap<>();
//        computeRegexpGroup(p, group, regexpFlags);
//
//        int sIndex = 0;
//
//        for (int i = 0; i < group.size(); i++) {
//            boolean isRegexp = regexpFlags.get(i);
//            String groupItem = group.get(i);
//            if (!isRegexp) {
//                for (int j = 0; j < groupItem.length(); j++) {
//                    if (sIndex == s.length() || s.charAt(sIndex++) != groupItem.charAt(j)) return false;
//                }
//            } else if (groupItem.length() == 1) {
//                if (sIndex++ == s.length()) return false;
//            } else {
//                //
//            }
//        }
//
//    }

    private static void computeRegexpGroup(String pattern, List<String> group, Map<Integer, Boolean> regexpFlags) {

    }
}
