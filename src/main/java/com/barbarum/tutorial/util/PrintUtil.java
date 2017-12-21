package com.barbarum.tutorial.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PrintUtil {

    public static void println(String prefix, int array[]) {
        List<Integer> result = Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println(String.format("%s %s", prefix, result));
    }

    public static void println(String prefix, String array[]) {
        List<String> result = Arrays.stream(array).collect(Collectors.toList());
        System.out.println(String.format("%s %s", prefix, result));
    }

    public static void println(String prefix, String inputA, String[] inputB) {
        List<String> result = Arrays.stream(inputB).collect(Collectors.toList());
        System.out.println(String.format("%s %s, %s", prefix, inputA, result));
    }

    public static void println(String prefix, String inputA, List<String> inputB) {
        System.out.println(String.format("%s %s, %s", prefix, inputA, inputB));
    }

    public static void println(String prefix, int target) {
        System.out.println(String.format("%s %s", prefix, target));
    }

    public static void println(String prefix, String target) {
        System.out.println(String.format("%s %s", prefix, target));
    }

    public static void println(int[] input, Function<int[], Integer> consumer) {
        println("Input ->", input);
        Integer output = consumer.apply(input);
        System.out.println(String.format("Output -> %s", output));
    }

    public static void println(String inputA, String[] inputB, BiFunction<String, String[], Boolean> consumer) {
        println("Input ->", inputA, inputB);
        Boolean output = consumer.apply(inputA, inputB);
        System.out.println(String.format("Output -> %s", output));
    }

    public static void println(String inputA, List<String> inputB, BiFunction<String, List<String>, Boolean> consumer) {
        println("Input ->", inputA, inputB);
        Boolean output = consumer.apply(inputA, inputB);
        System.out.println(String.format("Output -> %s", output));
    }
}
