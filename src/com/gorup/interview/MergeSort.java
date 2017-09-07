package com.gorup.interview;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MergeSort {
    public static void main(String[] args) {
        final List<Integer> sortMe = Arrays.asList(9, 1, 8, 3, 3,115, 15,1555,3,7,8,8,888,315,3141, 15, 1);

        System.out.println(String.format("Sorted: %s", sort(sortMe).stream()
                                .map((num) -> num.toString())
                                .collect(Collectors.joining(", "))));
    }

    public static List<Integer> sort(final List<Integer> toSort) {
        if (toSort.size() < 2) {
            return toSort;
        }

        final List<Integer> subLeft = new ArrayList<>();
        toSort.subList(0, toSort.size() / 2).forEach(subLeft::add);

        final List<Integer> subRight = new ArrayList<>();
        toSort.subList(toSort.size() / 2, toSort.size()).forEach(subRight::add);

        final List<Integer> sortedLeft = sort(subRight);
        final List<Integer> sortedRight = sort(subLeft);

        final List<Integer> sorted = new ArrayList<Integer>();

        while (!sortedLeft.isEmpty() || !sortedRight.isEmpty()) {
            if (sortedLeft.isEmpty() && !sortedRight.isEmpty()) {
                sorted.add(sortedRight.remove(0));
                continue;
            } else if (!sortedLeft.isEmpty() && sortedRight.isEmpty()) {
                sorted.add(sortedLeft.remove(0));
                continue;
            }

            if (sortedLeft.get(0) < sortedRight.get(0)) {
                sorted.add(sortedLeft.remove(0));
            } else {
                sorted.add(sortedRight.remove(0));
            }
        }
        return sorted;
    }
}
