/*Question 4-a)
    There are n tasks you need to complete for a game, labelled from 1 to n.
We are given r[i]=[x,y] representing a prerequisite relationship between task x and task y: task x has to be
completed before task y.
In one step you can complete any number of task as long as you have completed all the prerequisites for the tasks
you are provided while playing game.
Return the minimum number of steps needed to complete all tasks. If there is no way to complete all the tasks,
return -1.

Input: N = 3, r= [[1,3],[2,3]]
Output: 2

Explanation:
In the first step, you can complete task 1 and 2. In the second semester, step task 3 can be completed.

[5 Marks] 
*/

import java.util.*;

class Solution {
    public int minSteps(int N, int[][] r) {
        List<List<Integer>> graph = buildGraph(N, r);
        int[] indegree = calculateIndegree(N, r);
        Queue<Integer> queue = new LinkedList<>();

        // Find the tasks with no prerequisites
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            // Complete all tasks in the current step
            for (int i = 0; i < size; i++) {
                int currTask = queue.poll();
                N--;

                // Decrease the indegree of dependent tasks
                for (int nextTask : graph.get(currTask)) {
                    indegree[nextTask]--;

                    // If all prerequisites are completed, add the task to the queue
                    if (indegree[nextTask] == 0) {
                        queue.offer(nextTask);
                    }
                }
            }

            steps++;
        }

        return N == 0 ? steps : -1;
    }

    private List<List<Integer>> buildGraph(int N, int[][] r) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] relation : r) {
            int x = relation[0];
            int y = relation[1];
            graph.get(x).add(y);
        }

        return graph;
    }

    private int[] calculateIndegree(int N, int[][] r) {
        int[] indegree = new int[N + 1];
        for (int[] relation : r) {
            int y = relation[1];
            indegree[y]++;
        }

        return indegree;
    }
}

public class Main {
    public static void main(String[] args) {
        int N = 3;
        int[][] r = { { 1, 3 }, { 2, 3 } };

        Solution solution = new Solution();
        int minSteps = solution.minSteps(N, r);

        System.out.println("Minimum number of steps: " + minSteps);
    }
}

// -------------------------------------------------------------------------------------------------------------

/*
 * Question 4-b)
 * Given the root of a binary tree with unique values and the values of two
 * different nodes of the tree x and y,
 * return true if the nodes corresponding to the values x and y in the tree are
 * brothers, or false otherwise.
 * Two nodes of a binary tree are brothers if they have the same depth with
 * different parents.
 * Note that in a binary tree, the root node is at the depth 0, and children of
 * each depth k node are at the depth k + 1.
 * 
 * Input: root = [1,2,3,4], x = 4, y = 3
 * Output: false
 * 
 * [5 Marks]
 */

// class TreeNode {
// int val;
// TreeNode left;
// TreeNode right;

// TreeNode(int val) {
// this.val = val;
// }
// }

// class Solution {
// public boolean isBrothers(TreeNode root, int x, int y) {
// if (root == null)
// return false;

// // Queue to perform level order traversal
// Queue<TreeNode> queue = new LinkedList<>();
// queue.offer(root);

// while (!queue.isEmpty()) {
// boolean foundX = false;
// boolean foundY = false;
// int size = queue.size();

// for (int i = 0; i < size; i++) {
// TreeNode node = queue.poll();

// // Check if the current node is x or y
// if (node.val == x)
// foundX = true;
// if (node.val == y)
// foundY = true;

// // Check if the current node has different parents
// if (node.left != null && node.right != null) {
// if ((node.left.val == x && node.right.val == y) || (node.left.val == y &&
// node.right.val == x))
// return false;
// }

// // Add the children nodes to the queue
// if (node.left != null)
// queue.offer(node.left);
// if (node.right != null)
// queue.offer(node.right);
// }

// // If both x and y are found at the same depth, they are brothers
// if (foundX && foundY)
// return true;
// // If only one of x and y is found, they are not brothers
// if (foundX || foundY)
// return false;
// }

// return false;
// }
// }

// public class Main {
// public static void main(String[] args) {
// TreeNode root = new TreeNode(1);
// root.left = new TreeNode(2);
// root.right = new TreeNode(3);
// root.left.left = new TreeNode(4);

// int x = 4;
// int y = 3;

// Solution solution = new Solution();
// boolean areBrothers = solution.isBrothers(root, x, y);

// System.out.println("Are the nodes brothers? " + areBrothers);
// }
// }
