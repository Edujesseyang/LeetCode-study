package src.Tree;

import java.util.*;

public class BinaryTreePostOrderTraversal_145 {
    /*
    Given the root of a binary tree, return the postorder traversal of its nodes' values.



Example 1:

Input: root = [1,null,2,3]

Output: [3,2,1]

Explanation:



Example 2:

Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]

Output: [4,6,7,5,2,9,8,3,1]

Explanation:



Example 3:

Input: root = []

Output: []

Example 4:

Input: root = [1]

Output: [1]



Constraints:

The number of the nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100


Follow up: Recursive solution is trivial, could you do it iteratively?
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
        System.out.println("Recursive, Postorder: " + postorderRecursive(root));
        System.out.println("Iteration, Postorder: " + postorderIteration(root));
        System.out.println("Iteration with addFirst, Postorder: " + postorderIteration2(root));
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
        System.out.println("Recursive, Postorder: " + postorderRecursive(root_2));
        System.out.println("Iteration, Postorder: " + postorderIteration(root_2));
        System.out.println("Iteration with addFirst, Postorder: " + postorderIteration2(root_2));

    }

    // recursive:
    private static List<Integer> postorderRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    private static void traverse(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        traverse(node.left, result);
        traverse(node.right, result);
        result.add(node.val);
    }

    // iteration:
    private static List<Integer> postorderIteration(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            result.add(current.val); // 加末尾

            if (current.left != null) { // 先左
                stack.push(current.left);
            }

            if (current.right != null) { // 后右
                stack.push(current.right);
            }
        }

        Collections.reverse(result); // 关键步骤, return之前反转result list.
        return result;
    }

    // 利用LinkedList 的 addFirst method
    private static List<Integer> postorderIteration2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            ((LinkedList<Integer>) result).addFirst(current.val); // 进行一次强制类型转换, 为了使用addFirst

            if (current.left != null) { // 先左
                stack.push(current.left);
            }

            if (current.right != null) { // 后右
                stack.push(current.right);
            }
        }

        return result;
    }

}
