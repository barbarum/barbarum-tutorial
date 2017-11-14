package com.barbarum.tutorial.code.findAll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateParentheses {


    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);

        return result;
    }

    private void backtrack(List<String> list, String string, int front, int end, int maximum) {
        if (string.length() == maximum * 2) {
            list.add(string);
            return;
        }

        if (front < maximum) {
            backtrack(list, string + "(", front + 1, end, maximum);
        }

        if (end < front) {
            backtrack(list, string + ")", front, end + 1, maximum);
        }
    }

    public static void main(String args[]) {

    }
}
