package com.barbarum.tutorial.code.array;

public class FindMajorityElement {

    /**
     * Using Moore’s Voting Algorithm.
     */
    public static Integer findMajorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int candidate = findCandidate(nums);

        return validateCandidate(nums, candidate);
    }

    private static int findCandidate(int[] nums) {
        int candidate = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (candidate == nums[i]) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                candidate = nums[i];
                count = 1;
            }
        }

        return candidate;
    }


    private static Integer validateCandidate(int[] nums, int candidate) {

        for (int i = 0, count = 0; i < nums.length; i++) {
            if (nums[i] == candidate) {
                count++;

                if (count == nums.length / 2 + 1) {
                    return candidate;
                }
            }
        }

        return null;
    }


    public static void main(String args[]) {
        System.out.println(findMajorityElement(new int[]{3, 3, 4, 2, 4, 4, 2, 4, 4}));
        System.out.println(findMajorityElement(new int[]{3, 3, 4, 2, 4, 4, 2, 4}));

    }
}
