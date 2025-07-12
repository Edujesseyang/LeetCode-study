package src.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MinDepthOfBinaryTree_111 {
    /*
    Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 2
Example 2:

Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5


Constraints:

The number of nodes in the tree is in the range [0, 105].
-1000 <= Node.val <= 1000
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
        System.out.println("Max depth: " + minDepth_queue(root) + "  (BFS with queue)");
        System.out.println("Max depth: " + minDepth_recursion(root) + "  (with recursion)");
        System.out.println("Max depth: " + minDepth_stack(root) + "  (DFS with stack)");
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
        System.out.println("Max depth: " + minDepth_queue(root_2) + "  (BFS with queue)");
        System.out.println("Max depth: " + minDepth_recursion(root_2) + "  (with recursion)");
        System.out.println("Max depth: " + minDepth_stack(root_2) + "  (DFS with stack)");
    }

    // 最好的approach: 利用BFS level order 检索, 找到第一个leaf, 然后直接return此时的depth.
    private static int minDepth_queue(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                if (current.left == null && current.right == null) {
                    return depth;
                }

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            depth++;
        }
        return depth;
    }

    // 递归版本的approach:
    // 核心思路是:
    // 1. 如果为叶子, 返回0.
    // 2. 如果左子树为null,返回右子树的高加一. 如果右子树为空,返回左子树高加一.
    // 3. 其他则返回左子树高和右子树高中较小的那个再加一.
    private static int minDepth_recursion(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null) {
            return minDepth_recursion(root.right) + 1;
        }

        if (root.right == null) {
            return minDepth_recursion(root.left) + 1;
        }

        return Math.min(minDepth_recursion(root.left), minDepth_recursion(root.right)) + 1;
    }

    // DFS 的双stack版, 思路是深度优先检索每一条从根到叶子的路径, 然后每次到叶子是更新统计变量, 其初始值为 MAX_VALUE.
    private static int minDepth_stack(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(root);
        Stack<Integer> depths = new Stack<>();
        depths.push(1);

        int minDepth = Integer.MAX_VALUE;
        while (!nodes.isEmpty()) {
            TreeNode current = nodes.pop();
            int depth = depths.pop();

            if (current.left == null && current.right == null) {
                minDepth = Math.min(minDepth, depth);
            }

            if (current.left != null) {
                nodes.push(current.left);
                depths.push(depth + 1);
            }
            if (current.right != null) {
                nodes.push(current.right);
                depths.push(depth + 1);
            }
        }
        return minDepth;
    }
}
