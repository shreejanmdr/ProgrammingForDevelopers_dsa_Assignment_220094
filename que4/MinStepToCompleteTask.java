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

package que4;

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

public class MinStepToCompleteTask {
    public static void main(String[] args) {
        int N = 3;
        int[][] r = { { 1, 3 }, { 2, 3 } };

        Solution solution = new Solution();
        int minSteps = solution.minSteps(N, r);

        System.out.println("Minimum number of steps: " + minSteps);
    }
}
