package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.InputUtil;
import com.barbarum.tutorial.util.PrintUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkylineProblem {

    public static List<int[]> find(List<int[]> buildings) {
        if (buildings == null || buildings.size() == 0) {
            return Collections.emptyList();
        }
        return findSkyline(buildings, 0, buildings.size() - 1);
    }

    private static List<int[]> findSkyline(List<int[]> buildings, int start, int end) {
        List<int[]> result = new ArrayList<>();

        if (start == end) {
            result.add(new int[]{buildings.get(start)[0], buildings.get(start)[2]});
            result.add(new int[]{buildings.get(start)[1], 0});
            return result;
        }

        int mid = (start + end) / 2;

        List<int[]> left = findSkyline(buildings, start, mid);
        List<int[]> right = findSkyline(buildings, mid + 1, end);

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
        List<int[]> builds = InputUtil.buildIntArrayCollection(
                "2, 9, 10",
                "3, 6, 15",
                "5, 12, 12",
                "13, 16, 10",
                "13, 16, 10",
                "15, 17, 5");

        List<int[]> result = find(builds);

        System.out.println("Output ->");
        for (int[] item : result) {
            PrintUtil.println("", item);
        }
    }
}
