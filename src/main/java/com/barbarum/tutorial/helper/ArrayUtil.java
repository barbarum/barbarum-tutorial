package com.barbarum.tutorial.helper;

import com.google.common.base.Preconditions;

public class ArrayUtil {

    /**
     * Check if the row of given matrix is valid (Not null, and not empty).
     *
     * @param matrix the given matrix.
     * @param row    the given row.
     * @return true if it's valid, otherwise false.
     */
    public static boolean isValid(int[][] matrix, int row) {
        Preconditions.checkArgument(matrix != null && matrix.length > 0, "The given matrix can not be null or empty.");
        Preconditions.checkArgument(row >= 0 && row < matrix.length,
                "The given row (%s) is not in the row range [%s, %s) of the matrix.", row, 0, matrix.length);
        return matrix[row] != null && matrix[row].length > 0;
    }
}
