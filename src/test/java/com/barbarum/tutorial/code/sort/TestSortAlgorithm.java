package com.barbarum.tutorial.code.sort;

import com.barbarum.tutorial.util.AssertUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
class TestSortAlgorithm {

    @Parameter()
    private String description;

    @Parameter(1)
    private int[] testData;

    @Parameter(2)
    private int[] expectedData;

    @Parameters
    public static Collection<List<?>> setUp() {
        return AssertUtil.createDataset()
                // Simple normal test cases
                .add("Sort [3, 1]", "3, 1", "1, 3")
                .add("Sort [2,3,1]", "2, 3, 1", "1, 2, 3")
                // Edge cases
                .add("Sort []", "", "")
                .add("Sort [2]", "2", "2")
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
        AssertUtil.executeIntArray(this.description, consumer, this.testData, this.expectedData);
    }

}
