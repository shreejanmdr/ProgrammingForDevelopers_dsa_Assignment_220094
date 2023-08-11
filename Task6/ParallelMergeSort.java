/*
Task 6
    Write a Java program that uses multithreading to implement a parallel merge sort algorithm. Your program should
meet the following requirements:

1. Input: Your program should accept an array of integers as input.
2. Output: Your program should output the sorted array.
3. Algorithm: Your program should use a parallel merge sort algorithm to sort the input array. The algorithm
    should divide the input array into subarrays, sort the subarrays in parallel using multiple threads, and then
    merge the sorted subarrays to produce the final sorted array.
4. Performance: Your program should be optimized for performance, such that it sorts the input array as
    quickly as possible. You should experiment with different thread counts and input array sizes to find the
    optimal settings.
5. Error handling: Your program should handle errors and exceptions gracefully, such as by providing
    informative error messages and exiting gracefully.
6. Testing: Your program should be thoroughly tested to ensure that it correctly sorts the input array and
    produces the expected output.

    To complete this assignment, you will need to implement the parallel merge sort algorithm in Java using
multithreading. You should also experiment with different thread counts and input array sizes to find the optimal
settings for performance. You can use Java's built-in threading and synchronization features, such as the Thread
class and synchronized keyword, to implement the parallel merge sort algorithm.

[20 Marks]
 */

package Task6;

import java.util.Arrays;

public class ParallelMergeSort {
    public static void main(String[] args) {
        int[] arr = { 38, 27, 43, 3, 9, 82, 10 };
        int numThreads = 4; // You can experiment with different thread counts

        System.out.println("Orginal array: " + Arrays.toString(arr));

        parallelMergeSort(arr, numThreads);

        System.out.println("Sorted array: " + Arrays.toString(arr));
    }

    public static void parallelMergeSort(int[] arr, int numThreads) {
        if (numThreads <= 1) {
            mergeSort(arr);
            return;
        }

        int mid = arr.length / 2;

        int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
        int[] rightArr = Arrays.copyOfRange(arr, mid, arr.length);

        Thread leftThread = new Thread(() -> parallelMergeSort(leftArr, numThreads / 2));
        Thread rightThread = new Thread(() -> parallelMergeSort(rightArr, numThreads / 2));

        leftThread.start();
        rightThread.start();

        try {
            leftThread.join();
            rightThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(leftArr, rightArr, arr);
    }

    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        int mid = arr.length / 2;

        int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
        int[] rightArr = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(leftArr);
        mergeSort(rightArr);

        merge(leftArr, rightArr, arr);
    }

    public static void merge(int[] leftArr, int[] rightArr, int[] mergedArr) {
        int leftIdx = 0, rightIdx = 0, mergedIdx = 0;

        while (leftIdx < leftArr.length && rightIdx < rightArr.length) {
            if (leftArr[leftIdx] < rightArr[rightIdx]) {
                mergedArr[mergedIdx++] = leftArr[leftIdx++];
            } else {
                mergedArr[mergedIdx++] = rightArr[rightIdx++];
            }
        }

        while (leftIdx < leftArr.length) {
            mergedArr[mergedIdx++] = leftArr[leftIdx++];
        }

        while (rightIdx < rightArr.length) {
            mergedArr[mergedIdx++] = rightArr[rightIdx++];
        }
    }
}
