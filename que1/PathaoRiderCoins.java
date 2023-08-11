/*
Question 1-b)
    A group of n Pathao riders stood in a queue, and each rider was assigned a unique integer rating based on their
performance over the year. The Pathao company planned to distribute gold coins to each rider in ascending order,
starting from count 1. The riders with higher ratings should receive more coins than their neighboring riders. The
objective was to determine the minimum number of coins required by Pathao to distribute coins to the selected
riders according to their ratings.

Input: ratings = [1,0,2]
Output: 5

Explanation: You can give the first, second, and third rider 2, 1, 2 gold coins, respectively.

[5 Marks]
*/

package que1;

import java.util.Arrays;

public class PathaoRiderCoins {
    public static int minimumCoins(int[] ratings) {
        int n = ratings.length;
        int[] coins = new int[n];
        Arrays.fill(coins, 1); // Initialize all coins to 1

        // Traverse the ratings array from left to right
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                coins[i] = coins[i - 1] + 1; // Give more coins to the rider with a higher rating
            }
        }

        // Traverse the ratings array from right to left
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                coins[i] = Math.max(coins[i], coins[i + 1] + 1); // Update the coins if necessary
            }
        }

        // Calculate the total number of coins required
        int totalCoins = 0;
        for (int i = 0; i < n; i++) {
            totalCoins += coins[i];
        }

        return totalCoins;
    }

    public static void main(String[] args) {
        int[] ratings = { 1, 0, 2 };

        int result = minimumCoins(ratings);
        System.out.println("Minimum number of coins required: " + result);
    }
}
