package com.barbarum.tutorial.helper;

import static com.google.common.base.Preconditions.checkArgument;

public class Helper {

    /**
     * Swap two elements in the given array.
     *
     * @param data   the given array.
     * @param aIndex the index of item a.
     * @param bIndex the index of item b.
     * @throws IllegalArgumentException the given array is null or empty, or index of item a/b are not valid.
     */
    public static void swap(int[] data, int aIndex, int bIndex) {
        checkArgument(data != null && data.length > 0, "Array must not be empty.");
        checkArgument(aIndex >= 0 && aIndex < data.length,
                "index (%s) of item a must be between 0 and (%s)", aIndex, data.length);
        checkArgument(bIndex >= 0 && bIndex < data.length,
                "index (%s) of item b must be between 0 and (%s)", bIndex, data.length);

        int temp = data[aIndex];
        data[aIndex] = data[bIndex];
        data[bIndex] = temp;
    }
}
