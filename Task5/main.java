/*
Task 5-a) 
    Implement hill climbing algorithm
[5 Marks]
 */

package Task5;

import java.util.Arrays;

class HillClimbing {
    public static void main(String[] args) {
        int[] initialSolution = { 4, 2, 7, 1, 5 }; // Initial solution
        int[] finalSolution = hillClimbing(initialSolution);

        System.out.println("Initial Solution: " + Arrays.toString(initialSolution));
        System.out.println("Final Solution: " + Arrays.toString(finalSolution));
        System.out.println("Final Solution Fitness: " + evaluate(finalSolution));
    }

    public static int[] hillClimbing(int[] initialSolution) {
        int[] currentSolution = initialSolution;
        int currentFitness = evaluate(currentSolution);

        while (true) {
            int[] neighbor = getBestNeighbor(currentSolution);
            int neighborFitness = evaluate(neighbor);

            if (neighborFitness >= currentFitness) {
                break; // Stop if no better neighbor found
            }

            currentSolution = neighbor;
            currentFitness = neighborFitness;
        }

        return currentSolution;
    }

    public static int[] getBestNeighbor(int[] solution) {
        int n = solution.length;
        int[] bestNeighbor = Arrays.copyOf(solution, n);
        int bestFitness = evaluate(bestNeighbor);

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= 9; j++) {
                int[] neighbor = Arrays.copyOf(solution, n);
                neighbor[i] = (solution[i] + j) % 10;

                int neighborFitness = evaluate(neighbor);

                if (neighborFitness < bestFitness) {
                    bestNeighbor = neighbor;
                    bestFitness = neighborFitness;
                }
            }
        }

        return bestNeighbor;
    }

    public static int evaluate(int[] solution) {
        int fitness = 0;

        for (int i = 0; i < solution.length; i++) {
            fitness += solution[i];
        }

        return fitness;
    }
}
