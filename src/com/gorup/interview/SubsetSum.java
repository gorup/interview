package com.gorup.interview;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SubsetSum {
    public static void main(String[] args) {
        final List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(3);
        arr.add(6);
        arr.add(2);
        arr.add(4);
        arr.add(7);
        arr.add(8);
        System.err.println("Is there a subset to 9: " + subSum(arr, 9));
    }

    public static List<Integer> subSum(final List<Integer> nums, final Integer target) {
        System.err.println(String.format("target: %d nums: %s", target, nums.stream().map(Object::toString).collect(Collectors.joining(", "))));
        if (target == 0) {
            System.err.println("Target = 0, returning true");
            return new ArrayList<Integer>();
        }
        if (nums.isEmpty() || target < 0) {
            System.err.println("Empty list or taregt < 0, returning false");
            return null;
        }

        final Integer removed = nums.remove(0);
        final List<Integer> numsMinusElementLeft = new ArrayList<>(nums);
        final List<Integer> numsMinusElementRight = new ArrayList<>(nums);
        final List<Integer> successListLeft = subSum(numsMinusElementLeft, target - removed);
        final List<Integer> successListRight = subSum(numsMinusElementRight, target);
        if (successListLeft != null) {
            successListLeft.add(removed);
            return successListLeft;
        } else if (successListRight != null) {
            return successListRight;
        }
        return null;
    }

}
