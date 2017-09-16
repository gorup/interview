package com.gorup.interview;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class IsCyclic {
    public static class Node {
        public List<Node> neighbors;
        public String id;

        public Node(final String id) {
            this.neighbors = new ArrayList<Node>();
            this.id = id;
        }

        public String toString() {
            return id;
        }
    }

    public static void addEdge(final Node a, final Node b) {
        a.neighbors.add(b);
        b.neighbors.add(a);
    }

    public static void removeEdge(final Node a, final Node b) {
        a.neighbors.remove(b);
        b.neighbors.remove(a);
    }

    public static void main(String[] args) {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");

        addEdge(a, b);
        addEdge(a, c);
        addEdge(a, d);
        addEdge(a, e);
        addEdge(a, f);
//        addEdge(c, e); // makes cycle

        System.out.println("There is a cycle - " + isCycle(f));
    }

    public static boolean isCycle(final Node root) {
        final Set<Node> visited = new HashSet<>();

        final Queue<Node> toProcess = new LinkedList<>();
        toProcess.add(root);

        while(!toProcess.isEmpty()) {
            final Node curr = toProcess.remove();
            System.err.println("Looking at " + curr.id + curr.neighbors.toString());
            if (visited.contains(curr)) {
                return true;
            }
            visited.add(curr);
            final List<Node> toRemove = new ArrayList<>();
            curr.neighbors.stream()
                    .forEach((neighbor) -> {
                        if (neighbor == null) {
                            System.err.println("Nie");
                        }

                        System.err.println("neighbor is " + neighbor.id);
                        toRemove.add(neighbor);
                        toProcess.add(neighbor);
                    });
            toRemove.forEach((n) -> removeEdge(curr, n));

        }
        return false;
    }
}
