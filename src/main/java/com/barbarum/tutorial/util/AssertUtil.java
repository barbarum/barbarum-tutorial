package com.barbarum.tutorial.util;

import com.google.common.base.Preconditions;
import org.junit.Assert;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AssertUtil {


    public static final String SPLITOR = ",";
    public static final String ORIGINAL_SPLITOR_REGEXP = "[, -]*";

    /**
     * Generate an test data, recorded in a list.
     * 2. list.get(0) -> int[] sample data
     * 3. list.get(1) -> int[] expected data.
     *
     * @param sample   the same data.
     * @param expected the expected data.
     * @return a list records each test case.
     */
    public static Object[] generateTestData(String sample, String expected) {
        return new Object[]{convertIntCollection(sample), convertIntCollection(expected)};
    }

    public static DatasetList createDataset() {
        return new DatasetList();
    }

    private static Collection<Integer> convertIntCollection(String string) {
        if (string == null || string.isEmpty()) {
            return Collections.emptyList();
        }
        String temp = string.replaceAll(ORIGINAL_SPLITOR_REGEXP, SPLITOR);
        return Arrays.stream(temp.split(",", -1))
                .filter(BasicUtil::hasContent)
                .map(Integer::new)
                .collect(Collectors.toList());
    }

    public static void executeIntArray(Consumer<int[]> consumer, int[] sample, int[] expected) {
        Preconditions.checkArgument(consumer != null, "Consumer function must not be null.");
        consumer.accept(sample);
        Assert.assertArrayEquals(expected, sample);
    }

    public static class DatasetList {
        private List<Object[]> dataset;

        public DatasetList(List<Object[]> dataset) {
            this.dataset = dataset;
        }

        public DatasetList() {
            this(new ArrayList<>());
        }

        public DatasetList add(String sample, String expected) {
            this.dataset.add(generateTestData(sample, expected));
            return this;
        }

        public DatasetList add(int[] sample, int[] expected) {
            this.dataset.add(new Object[]{sample, expected});
            return this;
        }

        public List<Object[]> toList() {
            return new ArrayList<>(this.dataset);
        }
    }
}
