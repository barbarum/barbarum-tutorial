package com.barbarum.tutorial.code;

public class Helper {
    
    public static void swap(int[] data, int left, int right) {
        int temp = data[left];
        data[left] = data[right];
        data[right] = temp;
    }
}
