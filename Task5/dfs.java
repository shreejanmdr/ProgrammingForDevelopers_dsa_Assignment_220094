/*
Task5-b)
    A network consisting of n servers is connected in a tree structure, where the servers are numbered from 0 to n -
1 and there are n - 1 connections between them that only allow for one-way communication. A 2D array a is
used to represent these connections, where a[i] = [ai, bi] represents a directed path from server ai to server bi.
However, due to specific requirements, all traffic from each server must route to server 0. The task is to
reorient some connections to ensure that each server has a path to server 0. The goal is to minimize the number
of edges that need to be changed. It is guaranteed that every server must have a path to server 0 after the
connections are reordered.

Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3

Explanation: Change the direction of edges show in red such that each node can reach the node 0.

[5 Marks] 
*/
//Depth-First Search (DFS)

//not sure
package Task5;

import java.util.*;

class ReorientConnections {
    public static void main(String[] args) {
        int n = 6;
        int[][] connections = { { 0, 1 }, { 1, 3 }, { 2, 3 }, { 4, 0 }, { 4, 5 } };

        int minChanges = minReorientConnections(n, connections);
        System.out.println("Minimum number of edge changes required: " + minChanges);
    }

    public static int minReorientConnections(int n, int[][] connections) {
        List<List<Integer>> graph = buildGraph(n, connections);
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);

        int minChanges = bfs(0, graph, distances);

        return minChanges;
    }

    public static List<List<Integer>> buildGraph(int n, int[][] connections) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] connection : connections) {
            int from = connection[0];
            int to = connection[1];
            graph.get(from).add(to);
        }

        return graph;
    }

    public static int bfs(int start, List<List<Integer>> graph, int[] distances) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        distances[start] = 0;
        int minChanges = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : graph.get(node)) {
                if (distances[neighbor] > distances[node] + 1) {
                    distances[neighbor] = distances[node] + 1;
                    queue.offer(neighbor);
                } else {
                    // If the distance is not decreasing, it means the edge needs to be changed
                    minChanges++;
                }
            }
        }

        return minChanges;
    }
}
