package com.barbarum.tutorial.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryWatch {

    public static List<String> readBinaryWatch(int num) {
        // Loop num, split num for hours and minutes
        // -> get a list of possible hours combinations (list, total: 4, count, position, transient)
        // -> get a list of possible minutes combinations (list, total: 6, count, position)
        // Combine them together

        if (num == 0) {
            return Collections.singletonList("0:00");
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i <= num; i++) {
            backtrace(result, 0, 0, i, num - i, 0, 0);
        }

        return result;
    }

    private static void backtrace(List<String> result, int th, int tm, int hc, int mc, int hp, int mp) {
        if (hc == 0 && mc == 0) {
            if (th < 12 && tm < 60) {
                result.add("" + th + ":" + (tm < 10 ? "0" + tm : tm));
            }
            return;
        }

        if (hc > 0) {
            for (int i = hp; i < 4; i++) {
                backtrace(result, th + (1 << i), tm, hc - 1, mc, i + 1, mp);
            }
        } else {
            for (int i = mp; i < 6; i++) {
                backtrace(result, th, tm + (1 << i), hc, mc - 1, hp, i + 1);
            }
        }
    }

    public static void main(String args[]) {
        List<String> result = readBinaryWatch(2);
//        Collections.sort(result);
        System.out.println(result);
    }
}
