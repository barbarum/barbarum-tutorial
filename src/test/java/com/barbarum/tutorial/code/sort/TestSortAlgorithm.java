package com.barbarum.tutorial.code.sort;

import com.barbarum.tutorial.util.ArrayUtil;
import com.barbarum.tutorial.util.AssertUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.function.Consumer;

import static com.barbarum.tutorial.util.ArrayUtil.*;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestSortAlgorithm {

    @Parameter()
    public Collection<?> testData;

    @Parameter(1)
    public Collection<?> expectedData;

    @Parameters(name = "{index}: Sort {0}")
    public static Iterable<Object[]> setUp() {
        return AssertUtil.createDataset()

                // Simple normal test cases
                .add("3, 1", "1, 3")
                .add("2, 3, 1", "1, 2, 3")

                // Edge cases
                .add("", "")
                .add("2", "2")

                .toList();
    }

    @Test
    public void testQuickSort() {
        execute(SortAlgorithm::quickSort);
    }

    @Test
    public void testHeapSort() {
        execute(SortAlgorithm::heapSort);
    }

    @Test
    public void testMergeSort() {
        execute(SortAlgorithm::mergeSort);
    }

    private void execute(Consumer<int[]> consumer) {
        AssertUtil.executeIntArray(consumer
                , convertIntArray(this.testData)
                , convertIntArray(this.expectedData));
    }

}
