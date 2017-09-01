package com.gorup.makerake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Order {
    public static void main(String[] args) {
        final String target = "a";
        System.out.println(String.format("Build order for %s is: %s", target, String.join(", ", getBuildOrder(target, new LinkedHashSet<String>()))));
    }

    public static List<String> getBuildOrder(final String target, final Set<String> parentDeps) {
        final LinkedHashSet<String> orderedDependencies = new LinkedHashSet<String>();

        parentDeps.add(target);

        getDirectDependencies(target).forEach((dependency) -> {
            if (parentDeps.contains(dependency)) {
                throw new RuntimeException(String.format("Cycle detected, target %s depends on %s which is an ancestor", target, dependency));
            }
            orderedDependencies.addAll(getBuildOrder(dependency, parentDeps).reverse());
        });

        parentDeps.remove(target);

        orderedDependencies.add(target);
        return new ArrayList<>(orderedDependencies);
    }

    // Helper
    public static List<String> getDirectDependencies(final String target) {
        final Map<String, List<String>> directDeps = new HashMap<String, List<String>>();
        directDeps.put("a", Arrays.asList("b", "c", "r"));
        directDeps.put("r", Arrays.asList("y", "yy", "yyy"));
        directDeps.put("yy", Arrays.asList("c"));
        directDeps.put("yyy", Arrays.asList("z"));
        directDeps.put("b", Arrays.asList("e", "c"));
        directDeps.put("z", Arrays.asList("g", "h"));

        return directDeps.getOrDefault(target, Arrays.asList());
    }
}

