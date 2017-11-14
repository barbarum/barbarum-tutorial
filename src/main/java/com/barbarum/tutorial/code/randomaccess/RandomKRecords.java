package com.barbarum.tutorial.code.randomaccess;

import java.util.*;
import java.util.stream.IntStream;

public class RandomKRecords {


    public static int[] randomAccess(Iterator<Integer> stream, int k) {
        List<Integer> result = readFirstKRecords(stream, k);

        if (result.isEmpty() || !stream.hasNext()) {
            return getIntArray(result);
        }

        int countOfRead = k;
        Random random = new Random();

        while (stream.hasNext()) {
            countOfRead++;
            Integer data = stream.next();
            int indexToRemove = random.nextInt(countOfRead);
            if (indexToRemove >= 0 && indexToRemove < k) {
                result.set(indexToRemove, data);
            }
        }

        return getIntArray(result);
    }

    private static int[] getIntArray(List<Integer> result) {
        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static List<Integer> readFirstKRecords(Iterator<Integer> stream, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException();
        }

        int remaining = k;
        List<Integer> list = new ArrayList<>();
        while (remaining-- > 0 && stream.hasNext()) {
            list.add(stream.next());
        }
        return list;
    }

    public static void main(String args[]) {
        print(100, 7, 2 << 16L);
    }

    private static void print(int seed, int k, int loop) {
        printProbability(runLoop(seed, k, loop), loop);
    }

    private static int[] runLoop(int seed, int k, int loop) {
        int[] result = new int[seed];

        IntStream.range(0, loop)
                .flatMap((index) -> Arrays.stream(RandomKRecords.randomAccess(new Log(seed).iterator(), k)))
                .forEach((item) -> result[item]++);

        return result;
    }

    private static void printProbability(int[] result, int loop) {
        for (int i = 0; i < result.length; i++) {
            System.out.println(i + " -> " + (1.0 * result[i]) / loop);
        }
    }

    private static class Log implements Iterable<Integer> {

        private final Integer maximum;

        public Log(int maximum) {
            this.maximum = maximum;
        }

        @Override
        public Iterator<Integer> iterator() {

            return new Iterator<Integer>() {

                private int current = 0;

                @Override
                public boolean hasNext() {
                    return current < maximum;
                }

                @Override
                public Integer next() {
                    return this.current++;
                }
            };
        }
    }
}
