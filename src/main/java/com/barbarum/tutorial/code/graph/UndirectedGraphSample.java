package com.barbarum.tutorial.code.graph;

import java.util.*;

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 * int label;
 * List<UndirectedGraphNode> neighbors;
 * UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class UndirectedGraphSample {

    /**
     * Breadth-First Search
     *
     * @param root the given start node.
     * @return the new graph
     */
    public static UndirectedGraphNode clone(UndirectedGraphNode root) {
        if (root == null) {
            return null;
        }

        Queue<UndirectedGraphNode> queue = new LinkedList<>(); // Space: O(E)
        queue.offer(root);

        HashMap<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>(); // Space: O(V)
        UndirectedGraphNode newRoot = new UndirectedGraphNode(root.label);
        mapping.put(root, newRoot);

        // BFS to add neighbors for each vertex.
        while (!queue.isEmpty()) { // Time: O(E)
            UndirectedGraphNode oldNode = queue.poll();

            for (UndirectedGraphNode neighbor : oldNode.neighbors) {

                // Use mapping as visited flag, and also we only add new vertices into the queue.
                if (!mapping.containsKey(neighbor)) {
                    queue.offer(neighbor);
                }
                mapping.get(oldNode).neighbors
                        .add(mapping.computeIfAbsent(neighbor, (node) -> new UndirectedGraphNode(node.label)));
            }
        }

        return newRoot;
    }

    /**
     * Search graph via Breadth First Search
     *
     * @param starter the given start node.
     * @return a list of node value.
     */
    public static List<Integer> BFS(UndirectedGraphNode starter) {
        if (starter == null) {
            return Collections.emptyList();
        }

        // Initialize data structure
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        HashSet<UndirectedGraphNode> visited = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        queue.offer(starter);

        // Loop each node, and then its neighbors
        while (!queue.isEmpty()) {
            UndirectedGraphNode current = queue.poll();
            if (!visited.contains(current)) {
                result.add(current.label);
                visited.add(current);
                current.neighbors.forEach(queue::offer);
            }
        }

        return result;
    }

    public static List<Integer> DFS(UndirectedGraphNode starter) {
        if (starter == null) {
            return Collections.emptyList();
        }

        // Initialize data structure
        Deque<UndirectedGraphNode> stack = new LinkedList<>();
        HashSet<UndirectedGraphNode> visited = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        stack.push(starter);

        while (!stack.isEmpty()) {
            UndirectedGraphNode current = stack.pop();

            // skip visited vertex
            if (visited.contains(current)) {
                continue;
            }

            result.add(current.label);
            visited.add(current);

            for (int i = current.neighbors.size() - 1; i >= 0; i--) {
                stack.push(current.neighbors.get(i));
            }
        }

        return result;
    }

    private static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }

    public static void main(String args[]) {
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        UndirectedGraphNode node3 = new UndirectedGraphNode(3);
        UndirectedGraphNode node4 = new UndirectedGraphNode(4);
        UndirectedGraphNode node5 = new UndirectedGraphNode(5);
        UndirectedGraphNode node6 = new UndirectedGraphNode(6);


        node1.neighbors.addAll(Arrays.asList(node1, node2, node3));
        node2.neighbors.add(node1);
        node3.neighbors.add(node1);

        node2.neighbors.add(node4);
        node4.neighbors.add(node2);

        node5.neighbors.add(node6);
        node6.neighbors.add(node5);

        System.out.println("Old Graph (BFS) -> " + BFS(node1));
        System.out.println("Old Graph (DFS) -> " + DFS(node1));

        UndirectedGraphNode newGraph = clone(node1);

        System.out.println("New Graph (BFS) -> " + BFS(newGraph));
        System.out.println("New Graph (DFS) -> " + DFS(newGraph));
    }
}
