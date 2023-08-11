
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
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

class Solution {
    public boolean isBrothers(TreeNode root, int x, int y) {
        if (root == null)
            return false;

        // Queue to perform level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            boolean foundX = false;
            boolean foundY = false;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // Check if the current node is x or y
                if (node.val == x)
                    foundX = true;
                if (node.val == y)
                    foundY = true;

                // Check if the current node has different parents
                if (node.left != null && node.right != null) {
                    if ((node.left.val == x && node.right.val == y) || (node.left.val == y &&
                            node.right.val == x))
                        return false;
                }

                // Add the children nodes to the queue
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }

            // If both x and y are found at the same depth, they are brothers
            if (foundX && foundY)
                return true;
            // If only one of x and y is found, they are not brothers
            if (foundX || foundY)
                return false;
        }

        return false;
    }
}

public class ques4b {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        int x = 4;
        int y = 3;

        Solution solution = new Solution();
        boolean areBrothers = solution.isBrothers(root, x, y);

        System.out.println("Are the nodes brothers? " + areBrothers);
    }
}
