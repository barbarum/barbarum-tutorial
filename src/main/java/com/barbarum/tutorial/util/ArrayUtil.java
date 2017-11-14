package com.barbarum.tutorial.util;

import java.util.Collection;

import static com.barbarum.tutorial.util.BasicUtil.hasContent;
import static com.barbarum.tutorial.util.BasicUtil.inRangeOf;
import static com.google.common.base.Preconditions.checkArgument;

public class ArrayUtil {

    /**
     * Check if the row of given matrix is valid (Not null, and not empty).
     *
     * @param matrix the given matrix.
     * @param row    the given row.
     * @return true if it's valid, otherwise false.
     */
    public static boolean isValid(int[][] matrix, int row) {
        checkArgument(matrix != null && matrix.length > 0, "The given matrix can not be null or empty.");
        checkArgument(row >= 0 && row < matrix.length,
                "The given row (%s) is not in the row range [%s, %s) of the matrix.", row, 0, matrix.length);

        return matrix[row] != null && matrix[row].length > 0;
    }

    /**
     * Swap two elements in the given array.
     *
     * @param data   the given array.
     * @param aIndex the index of item a.
     * @param bIndex the index of item b.
     * @throws IllegalArgumentException the given array is null or empty, or index of item a/b are not valid.
     */
    public static void swap(int[] data, int aIndex, int bIndex) {
        checkArgument(hasContent(data), "Array must not be empty.");
        checkArgument(inRangeOf(data, aIndex), "index (%s) of item a must be between 0 and (%s)", aIndex, data.length);
        checkArgument(inRangeOf(data, bIndex), "index (%s) of item b must be between 0 and (%s)", bIndex, data.length);

        if (aIndex == bIndex) {
            return;
        }

        int temp = data[aIndex];
        data[aIndex] = data[bIndex];
        data[bIndex] = temp;
    }

    /**
     * Convert integer collection into integer array.
     *
     * @param collection the given integer collection.
     * @return the generated array.
     */
    public static int[] convertIntArray(Collection<?> collection) {
        return collection.stream()
                .map(Object::toString)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
