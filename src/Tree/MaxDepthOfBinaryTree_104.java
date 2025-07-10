package src.Tree;

import java.util.Stack;

public class MaxDepthOfBinaryTree_104 {
    /*
    Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 3
Example 2:

Input: root = [1,null,2]
Output: 2


Constraints:

The number of nodes in the tree is in the range [0, 104].
-100 <= Node.val <= 100
    */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        System.out.println("*** Testing ***");
        // Tree 1:
        System.out.println("Tree 1: ");
        System.out.println("        1\n" +
                "       / \\\n" +
                "      2   3\n" +
                "       \\  / \\\n" +
                "        4 5  6\n" +
                "           \\   \\\n" +
                "            7   8\n");
        // level 4
        TreeNode node_7 = new TreeNode(7, null, null);
        TreeNode node_8 = new TreeNode(8, null, null);
        // level 3
        TreeNode node_4 = new TreeNode(4, null, null);
        TreeNode node_5 = new TreeNode(5, node_7, null);
        TreeNode node_6 = new TreeNode(6, null, node_8);
        // lever 2
        TreeNode node_2 = new TreeNode(2, null, node_4);
        TreeNode node_3 = new TreeNode(3, node_5, node_6);
        // level 1
        TreeNode root = new TreeNode(1, node_2, node_3);
        System.out.println("Max depth: " + maxDepth(root) + "  (recursion)");
        System.out.println("Max depth: " + maxDepth2(root) + "  (iteration)");
        //Tree 2:
        System.out.println("Tree 2:");
        System.out.println("        1\n" +
                "       / \\\n" +
                "      2   3\n" +
                "     / \\  /\n" +
                "    4  5 6\n" +
                "   /    \\\n" +
                "  7      8\n");
        // level 4
        TreeNode node_7_2 = new TreeNode(7, null, null);
        TreeNode node_8_2 = new TreeNode(8, null, null);
        // level 3
        TreeNode node_4_2 = new TreeNode(4, node_7_2, null);
        TreeNode node_5_2 = new TreeNode(5, null, node_8_2);
        TreeNode node_6_2 = new TreeNode(6, null, null);
        // lever 2
        TreeNode node_2_2 = new TreeNode(2, node_4_2, node_5_2);
        TreeNode node_3_2 = new TreeNode(3, node_6_2, null);
        // level 1
        TreeNode root_2 = new TreeNode(1, node_2_2, node_3_2);
        System.out.println("Max depth: " + maxDepth(root_2) + "  (recursion)");
        System.out.println("Max depth: " + maxDepth2(root_2) + "  (iteration)");
    }

    // 用recursion的方法来做,  思路是: 根的 depth = 左叶的 depth 和右叶的 depth 中较大的那一个 + 1
    private static int maxDepth(TreeNode root) {
        // base case
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
        // 或者直接 return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // 用iteration的方法来做, 用两个stack 分别记录node和level
    private static int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(root);
        Stack<Integer> depths = new Stack<>();
        depths.push(1);

        int maxDepth = 0;
        while (!nodes.isEmpty()) {
            TreeNode current = nodes.pop();
            int depth = depths.pop();

            maxDepth = Math.max(maxDepth, depth);

            if (current.left != null) {
                nodes.push(current.left);
                depths.push(depth + 1);
            }

            if (current.right != null) {
                nodes.push(current.right);
                depths.push(depth + 1);
            }
        }

        return maxDepth;
    }
}
