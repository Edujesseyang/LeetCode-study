package src.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class UnivaluedBinaryTree_965 {

    /*A binary tree is uni-valued if every node in the tree has the same value.

    Given the root of a binary tree, return true if the given tree is uni-valued, or false otherwise.
    *
            1
            /\
           1  1
          /\   \
         1  1   1    (True)

            1
            /\
           1  1
          /\  /\
         5  1 1 1    (False)

    Constraints:

    The number of nodes in the tree is in the range [1, 100].
    0 <= Node.val < 100

    * */
    private static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(1);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(1);
        root1.right = new TreeNode(1);
        root1.right.left = new TreeNode(1);
        root1.right.right = new TreeNode(1);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(1);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(1);
        root2.right = new TreeNode(1);
        root2.right.left = new TreeNode(5);
        root2.right.right = new TreeNode(1);

        System.out.println("Test case 1: \nExpected: True, output: " + approach(root1));
        System.out.println("Test case 2: \nExpected: False, output: " + approach(root2));
    }

    private static boolean approach(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current != null && current.val != root.val) {
                return false;
            } else {
                if (current == null) {
                    continue;
                }
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        return true;
    }

    /*
     * 任何简单的搜索都可以, 广度优先, 深度优先 都无所谓. */
}
