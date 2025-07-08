package src.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal_102 {
    /*
    Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 2000].
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
        System.out.println("*** Testing nested list ***");
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
        System.out.println(levelOrder(root));

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
        System.out.println(levelOrder(root_2));
        System.out.println("Tree 1 should be same as Tree 2");

        System.out.println("\n*** Testing single list ***");
        System.out.println("Tree 1:");
        System.out.println(levelOrderEasy(root));
        System.out.println("Tree 2:");
        System.out.println(levelOrderEasy(root_2));

    }


    /**
     * Level order nested list
     *
     * @param : TreeNode root
     * @return : List<List<Integer>> result
     */
    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>(); // create returning var
        if (root == null) { // return if root is null
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>(); // create queue
        queue.offer(root); // offer root to queue

        while (!queue.isEmpty()) {
            List<Integer> subList = new ArrayList<>();
            int size = queue.size(); // determine the size of the sublist

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll(); // poll out a current
                assert current != null;
                subList.add(current.val); // add its val to sublist

                if (current.left != null) { // if there is a left leaf, offer it to the queue
                    queue.offer(current.left);
                }

                if (current.right != null) { // if there is a right leaf, offer it to the queue
                    queue.offer(current.right);
                }
            }
            result.add(subList); // add the sublist to the result list
        }
        return result;
    }

    // 如果此题简单化为只要一个 List 把所有 value 按照 level order 放进去即可, 该题可以简化为:
    private static List<Integer> levelOrderEasy(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(current.val);

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return result;
    }
}
