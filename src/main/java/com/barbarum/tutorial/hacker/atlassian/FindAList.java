package com.barbarum.tutorial.hacker.atlassian;

import java.util.*;

public class FindAList {


    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner scanner = new Scanner(System.in);

        List<String> list1 = readList(scanner);
        List<String> list2 = readList(scanner);

        System.out.println(find(list1, list2));
    }


    public static int find(List<String> list1, List<String> list2) {
        if (list1.size() < list2.size() || list2.isEmpty()) {
            return -1;
        }
        int i = list1.size() - 1;
        int j = list2.size() - 1;
        for (; i >= 0 && j >= 0; i--, j--) {
            if (!list1.get(i).equals(list2.get(j))) {
                j = list2.size();
            }
        }
        return j == -1 ? i + 1 : -1;

    }

    private static List<String> readList(Scanner scanner) {
        int length = scanner.nextInt();
        int index = 0;

        List<String> result = new ArrayList<>();
        while ((index++) < length) {
            result.add(scanner.next());
        }

        return result;
    }
}
