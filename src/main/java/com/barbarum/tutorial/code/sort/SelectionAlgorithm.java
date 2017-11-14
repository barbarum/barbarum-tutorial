package com.barbarum.tutorial.code.sort;

import com.barbarum.tutorial.util.ArrayUtil;

import static com.barbarum.tutorial.util.BasicUtil.hasContent;
import static com.barbarum.tutorial.util.BasicUtil.inRangeOf;
import static com.google.common.base.Preconditions.checkArgument;

public class SelectionAlgorithm {


    /**
     * Find kth smallest element in the array.
     *
     * @param data the given unordered array.- Average TC: O(n)
     * @param k    the kth smallest element.
     * @return
     */
    public static int quickSelect(int[] data, int k) {
        checkArgument(hasContent(data), "Data array must not be empty.");
        checkArgument(inRangeOf(data, k - 1),
                "Index k (%s) must be in the range of [1, (%s)\\).", k, data.length + 1);
        return quickSelect(data, k - 1, 0, data.length - 1);
    }

    private static int quickSelect(int[] data, int target, int start, int end) {
        if (start == end) {
            checkArgument(target == start,
                    "target index (%s) must be equal to the start index of the array if chose from one-element-array.", target);
            return data[target];
        }

        int p = partition(data, start, end);

        if (p == target) {
            return data[target];
        } else if (target < p) {
            return quickSelect(data, target, start, p - 1);
        } else {
            return quickSelect(data, target, p + 1, end);
        }
    }

    /**
     * Lomuto partition scheme
     */
    private static int partition(int[] data, int start, int end) {
        int index = start;
        int current = start;
        int pivot = findPivot(data, start, end);

        for (; current <= end - 1; current++) {
            if (data[current] < pivot) {
                ArrayUtil.swap(data, index, current);
                index++;
            }
        }

        if (data[index] != pivot) {
            ArrayUtil.swap(data, index, end);
        }

        return index;
    }

    private static int findPivot(int[] data, int start, int end) {
        return data[end];
    }
}
