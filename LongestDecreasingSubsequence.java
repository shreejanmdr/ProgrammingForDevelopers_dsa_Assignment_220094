/*
Question 2-a)
    Given an integer array nums and another integer k, the goal is to find the longest subsequence of nums that
satisfies the following two conditions:
The subsequence is strictly decreasing.
The difference between adjacent elements in the subsequence is at most k.
The output should be the length of the longest subsequence that meets these requirements.

For example, consider the following input:
nums = [8,5,4, 2, 1, 4, 3, 4, 3, 1, 15] k = 3
output=[8,5,4,2,1] or [8,5,4,3,1]
Output: 5

Explanation:
The longest subsequence that meets the requirements is [8,5,4,2,1] or [8,5,4,3,1].
The subsequence has a length of 5, so we return 5.
Note that the subsequence [1,3,4,5,8,15] does not meet the requirements because 15 - 8 = 7 is larger than 3.

[5 Marks]
 */

import java.util.Arrays;

public class LongestDecreasingSubsequence {
    public static int longestSubsequence(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];

        // Initialize the dp array with 1 as the minimum subsequence length is 1
        Arrays.fill(dp, 1);

        // Traverse the nums array from right to left
        for (int i = n - 2; i >= 0; i--) {
            // Check the elements after i to find the maximum subsequence length
            for (int j = i + 1; j < n; j++) {
                // Check if the conditions for a valid subsequence are satisfied
                if (nums[i] > nums[j] && nums[i] - nums[j] <= k) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // Update the subsequence length if necessary
                }
            }
        }

        // Find the maximum subsequence length
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = { 8, 5, 4, 2, 1, 4, 3, 4, 3, 1, 15 };
        int k = 3;

        int result = longestSubsequence(nums, k);
        System.out.println("Length of the longest subsequence: " + result);
    }
}
