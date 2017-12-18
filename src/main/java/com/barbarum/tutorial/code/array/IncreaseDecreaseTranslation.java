package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

public class IncreaseDecreaseTranslation {

    public static int[] translate(String table) {
        int result[] = new int[table.length() + 1];
        int current = 1;

        result[table.length()] = current++;

        for (int i = table.length() - 1; i >= 0; i--) {
            if (table.charAt(i) == 'd') {
                result[i] = current++;
            } else {
                result[i] = result[i + 1];
                result[i + 1] = current++;
            }
        }

        return result;
    }

    public static void main(String args[]) {
        PrintUtil.println("Output->", translate("di"));
        PrintUtil.println("Output->", translate("ddidi"));
    }
}
