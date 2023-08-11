/*
Question 2-b)
    Given an integer value k and an array of integers representing blacklisted ports, create an algorithm that outputs a
random port (an integer value between 0 and k-1) that is also a whitelisted port (meaning it is not in the array of
blacklisted ports). The goal is to minimize the number of built-in random calls in the algorithm.
The program should have two inputs: k, an integer value, and blacklisted_ports, an array of integers. The program
should also have a get() function that returns a whitelisted random number between 0 and k-1. The algorithm
should be optimized to reduce the number of built-in random calls required.

Example 1:
Input
["Program", "get", "get "get", "get", "get"]
[[7, [2, 3, 5]], [], [], [], [], [], [], []]
Output
[null, 0, 4, 6,1,4]

Explanation
program p = new program(7, [2, 3, 5]);
p.get(); // return 0, any number from [0,1,4,6] should be ok. Note that for every call of pick,
 // 0, 1, 4, and 6 must be equally likely to be returned (i.e., with probability 1/4).
p.get(); // return 4

[5 Marks]
 */

package que2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomWhiteListedPort {
    private int k;
    private Set<Integer> whitelist;
    private Random random;

    public RandomWhiteListedPort(int k, int[] blacklisted_ports) {
        this.k = k;
        this.whitelist = new HashSet<>();
        for (int i = 0; i < k; i++) {
            whitelist.add(i);
        }
        for (int port : blacklisted_ports) {
            whitelist.remove(port);
        }
        this.random = new Random();
    }

    public int get() {
        int randomIndex = random.nextInt(whitelist.size());
        int randomPort = 0;
        for (int port : whitelist) {
            if (randomIndex == 0) {
                randomPort = port;
                break;
            }
            randomIndex--;
        }
        return randomPort;
    }

    public static void main(String[] args) {
        int k = 7;
        int[] blacklisted_ports = { 2, 3, 5 };

        RandomWhiteListedPort program = new RandomWhiteListedPort(k, blacklisted_ports);
        System.out.println(program.get()); // Return a whitelisted random port
        System.out.println(program.get());
        System.out.println(program.get());
        System.out.println(program.get());
        System.out.println(program.get());
    }
}
