package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.ArrayUtil;
import com.barbarum.tutorial.util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Skyline {

    public static void find(List<int[]> buidings, List<int[]> skyline) {
        List<int[]> result = findSkyline(buidings, 0, buidings.size() - 1);
        skyline.addAll(result);
    }

    private static List<int[]> findSkyline(List<int[]> buidings, int start, int end) {
        List<int[]> result = new ArrayList<>();

        if (start == end) {
            result.add(new int[]{buidings.get(start)[0], buidings.get(start)[2]});
            result.add(new int[]{buidings.get(start)[1], 0});
            return result;
        }

        int mid = (start + end) / 2;

        List<int[]> left = findSkyline(buidings, start, mid);
        List<int[]> right = findSkyline(buidings, mid + 1, end);

        merge(result, left, right);

        return result;
    }

    private static void merge(List<int[]> result, List<int[]> left, List<int[]> right) {
        int leftLastSeenHeight = 0;
        int rightLastSeenHeight = 0;

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex != left.size() && rightIndex != right.size()) {
            int leftX = left.get(leftIndex)[0];
            int rightX = right.get(rightIndex)[0];

            int leftHeight = left.get(leftIndex)[1];
            int rightHeight = right.get(rightIndex)[1];

            int[] pointToAdd = new int[2];

            if (leftX < rightX) {
                leftLastSeenHeight = leftHeight;
                pointToAdd[0] = leftX;
                leftIndex++;
            } else if (rightX < leftX) {
                rightLastSeenHeight = rightHeight;
                pointToAdd[0] = rightX;
                rightIndex++;
            } else {
                leftLastSeenHeight = leftHeight;
                rightLastSeenHeight = rightHeight;
                pointToAdd[0] = leftLastSeenHeight > rightLastSeenHeight ? leftX : rightX;
                leftIndex++;
                rightIndex++;
            }

            pointToAdd[1] = Math.max(leftLastSeenHeight, rightLastSeenHeight);

            if (result.isEmpty() || pointToAdd[1] != result.get(result.size() - 1)[1]) {
                result.add(pointToAdd);
            }
        }

        while (leftIndex != left.size()) {
            result.add(left.get(leftIndex++));
        }

        while (rightIndex != right.size()) {
            result.add(right.get(rightIndex++));
        }
    }

    public static void main(String args[]) {
        List<int[]> builds = ArrayUtil.buildIntArrayCollection(
                "2, 9, 10",
                "3, 6, 15",
                "5, 12, 12",
                "13, 16, 10",
                "13, 16, 10",
                "15, 17, 5");

        List<int[]> result = new ArrayList<>();

        find(builds, result);

        System.out.println("Output ->");
        for (int[] item : result) {
            PrintUtil.println("", item);
        }
    }
}
