package com.barbarum.tutorial.util;

import com.google.common.base.Preconditions;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class AssertUtil {


    public static final String SPLITOR = ",";
    public static final String ORIGINAL_SPLITOR_REGEXP = "[, -]*";

    /**
     * Generate an test data, recorded in a list.
     * 1. list.get(0) -> test description
     * 2. list.get(1) -> int[] sample data
     * 3. list.get(2) -> int[] expected data.
     *
     * @param description the given test description.
     * @param sample      the same data.
     * @param expected    the expected data.
     * @return a list records each test case.
     */
    public static List<?> generateTestData(String description, String sample, String expected) {
        return Arrays.asList(description, convertIntArray(sample), convertIntArray(expected));
    }

    public static DatasetList createDataset() {
        return new DatasetList();
    }

    private static int[] convertIntArray(String string) {
        if (string == null || string.isEmpty()) {
            return new int[0];
        }
        String temp = string.replaceAll(ORIGINAL_SPLITOR_REGEXP, SPLITOR);

        return Arrays.stream(temp.split(",", -1))
                .filter(BasicUtil::hasContent)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void executeIntArray(String description, Consumer<int[]> consumer, int[] sample, int[] expected) {
        Preconditions.checkArgument(consumer != null, "Consumer function must not be null.");
        consumer.accept(sample);
        Assert.assertArrayEquals("Test (" + description + ") failed", expected, sample);
    }

    public static class DatasetList {
        private List<List<?>> dataset;

        public DatasetList(List<List<?>> dataset) {
            this.dataset = dataset;
        }

        public DatasetList() {
            this(new ArrayList<>());
        }

        public DatasetList add(String testDescription, String sample, String expected) {
            this.dataset.add(generateTestData(testDescription, sample, expected));
            return this;
        }

        public List<List<?>> toList() {
            return new ArrayList<>(this.dataset);
        }
    }
}
