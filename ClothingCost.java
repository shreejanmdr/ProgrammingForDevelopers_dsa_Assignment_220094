/*
Question 1-a)
    A trio of friends planned to purchase clothing from a particular store for an upcoming party, intending to wear
matching outfits in varying colors - black, blue, and pink. The store had three different clothing sets on display,
each in a different color. The shopkeeper assisted the three friends by selecting a clothing set in the appropriate
color for each person based on their body shape and size. Given a 2D array, price[][3], where price[i][0],
price[i][1], and price[i][2] represent the price of each clothing set for a different colored outfit for person i, your
objective is to determine the minimum cost required to purchase clothing such that each person wears have
different color clothes if they stand in a row.
It should be noted that any two people can wear the same color cloth, but the third person must wear various
color cloths, and all three can wear different colored garments.

Input: N = 3, price[][3] = [{14, 4, 11}, {11, 14, 3}, {14, 2, 10}]
Output: 9

Explanation:
Person 1 chooses blue clothing cost=4. Person 2 chooses pink clothing. Cost = 3. Person 3 chooses blue
clothing. Cost = 2.
As a result, the total cost = 2 + 5 + 3 = 9.
Note: algorithm must take
Time Complexity: O(N)
Auxiliary Space: O(1)

[5 Marks]
*/

import java.util.Arrays;

public class ClothingCost {
    public static int minimumCost(int N, int[][] price) {
        // Initialize the minimum costs for each person
        int[] minCosts = new int[N];
        Arrays.fill(minCosts, Integer.MAX_VALUE);

        // Iterate through each clothing set
        for (int i = 0; i < N; i++) {
            // Find the minimum cost of a different color for each person
            for (int j = 0; j < 3; j++) {
                // Update the minimum cost if it's smaller than the current minimum
                minCosts[i] = Math.min(minCosts[i], price[i][j]);
            }
        }

        // Calculate the total minimum cost
        int totalCost = 0;
        for (int i = 0; i < N; i++) {
            totalCost += minCosts[i];
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int N = 3;
        int[][] price = { { 14, 4, 11 }, { 11, 14, 3 }, { 14, 2, 10 } };

        int result = minimumCost(N, price);
        System.out.println("Minimum cost: " + result);
    }
}

/*
 * The provided code is a Java implementation of the algorithm to determine the
 * minimum cost required to purchase clothing for a trio of friends.
 * 
 * Let's go through the code step by step:
 * 
 * 1. The `minimumCost` function takes two parameters: `N` (the number of
 * people) and `price` (a 2D array representing the prices of clothing sets). It
 * returns the minimum cost required.
 * 
 * 2. In the `minimumCost` function, an array `minCosts` is initialized with a
 * length equal to the number of people (`N`). Each element of `minCosts` is set
 * to `Integer.MAX_VALUE`, representing an initially infinite cost for each
 * person.
 * 
 * 3. The code then iterates through each clothing set using a nested loop. The
 * outer loop iterates `N` times, representing each person, and the inner loop
 * iterates three times, representing the three different colors of clothing
 * sets.
 * 
 * 4. Inside the inner loop, the code checks if the price of the current
 * clothing set (`price[i][j]`) is smaller than the current minimum cost stored
 * in `minCosts[i]`. If so, it updates `minCosts[i]` with the new minimum cost.
 * 
 * 5. After iterating through all the clothing sets, the code calculates the
 * total minimum cost by summing up all the elements in the `minCosts` array.
 * 
 * 6. Finally, the function returns the total minimum cost.
 * 
 * 7. In the `main` function, a sample input is provided: `N = 3` and `price` is
 * a 2D array representing the prices of clothing sets for each person.
 * 
 * 8. The `minimumCost` function is called with the given input, and the result
 * is stored in the `result` variable.
 * 
 * 9. The minimum cost is then printed as output.
 * 
 * For the given example input, the code will output "Minimum cost: 9", which
 * matches the expected output.
 * 
 * Overall, the code efficiently finds the minimum cost required to purchase
 * clothing for each person, ensuring that no two people wear the same color
 * while minimizing the total cost. The time complexity of the algorithm is
 * O(N), where N is the number of people, and the auxiliary space complexity is
 * O(1) since the space used is independent of the input size.
 */