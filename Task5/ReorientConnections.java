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

package Task5;

import java.util.ArrayList;
import java.util.List;

public class ReorientConnections {
    public int minReorientConnections(int n, int[][] connections) {
        // Create the adjacency list to represent the tree
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] connection : connections) {
            int ai = connection[0];
            int bi = connection[1];
            graph[ai].add(bi);
        }

        // Perform DFS to count the edges that need to be reversed
        int[] reversedEdges = new int[1]; // Use an array to store the count as it will be modified within DFS
        boolean[] visited = new boolean[n];
        dfs(0, graph, visited, reversedEdges);

        return reversedEdges[0];
    }

    private void dfs(int node, List<Integer>[] graph, boolean[] visited, int[] reversedEdges) {
        visited[node] = true;
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                reversedEdges[0]++;
                dfs(neighbor, graph, visited, reversedEdges);
            }
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] connections = { { 0, 1 }, { 1, 3 }, { 2, 3 }, { 4, 0 }, { 4, 5 } };

        ReorientConnections solution = new ReorientConnections();
        int result = solution.minReorientConnections(n, connections);
        System.out.println("Minimum edges to be reversed: " + result); // Output: 3
    }
}
