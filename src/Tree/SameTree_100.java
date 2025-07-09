package src.Tree;

public class SameTree_100 {
    /*
    Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.



Example 1:


Input: p = [1,2,3], q = [1,2,3]
Output: true
Example 2:


Input: p = [1,2], q = [1,null,2]
Output: false
Example 3:


Input: p = [1,2,1], q = [1,1,2]
Output: false


Constraints:

The number of nodes in both trees is in the range [0, 100].
-104 <= Node.val <= 104
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
        //Tree 1:
        TreeNode a4 = new TreeNode(4, null, null);
        TreeNode a5 = new TreeNode(5, null, null);
        TreeNode a6 = new TreeNode(6, null, null);
        TreeNode a3 = new TreeNode(3, a5, a6);
        TreeNode a2 = new TreeNode(2, a4, null);
        TreeNode a1 = new TreeNode(1, a2, a3);

        //Tree 2:
        TreeNode b4 = new TreeNode(4, null, null);
        TreeNode b5 = new TreeNode(5, null, null);
        TreeNode b6 = new TreeNode(6, null, null);
        TreeNode b3 = new TreeNode(3, b5, b6);
        TreeNode b2 = new TreeNode(2, b4, null);
        TreeNode b1 = new TreeNode(1, b2, b3);

        System.out.println("Is Tree1 == Tree2 : " + isSameTree(a1, b1) + " Expected: True");

        //Tree 3:
        TreeNode c4 = new TreeNode(4, null, null);
        TreeNode c5 = new TreeNode(5, null, null);
        TreeNode c6 = new TreeNode(6, null, null);
        TreeNode c3 = new TreeNode(3, c6, null);
        TreeNode c2 = new TreeNode(2, c4, c5);
        TreeNode c1 = new TreeNode(1, c2, c3);

        //Tree 4:
        TreeNode d4 = new TreeNode(4, null, null);
        TreeNode d5 = new TreeNode(5, null, null);
        TreeNode d6 = new TreeNode(6, null, null);
        TreeNode d3 = new TreeNode(3, d5, d6);
        TreeNode d2 = new TreeNode(2, d4, null);
        TreeNode d1 = new TreeNode(1, d2, d3);

        System.out.println("Is Tree3 == Tree4 : " + isSameTree(c1, d1) + " Expected: False");

        //Tree 3:
        TreeNode e4 = new TreeNode(4, null, null);
        TreeNode e5 = new TreeNode(5, null, null);
        TreeNode e6 = new TreeNode(6, null, null);
        TreeNode e3 = new TreeNode(3, e6, null);
        TreeNode e2 = new TreeNode(2, e4, e5);
        TreeNode e1 = new TreeNode(1, e2, e3);

        //Tree 4:
        TreeNode f4 = new TreeNode(4, null, null);
        TreeNode f5 = new TreeNode(500, null, null);
        TreeNode f6 = new TreeNode(6, null, null);
        TreeNode f3 = new TreeNode(3, f6, null);
        TreeNode f2 = new TreeNode(2, f4, f5);
        TreeNode f1 = new TreeNode(1, f2, f3);

        System.out.println("Is Tree3 == Tree4 : " + isSameTree(e1, f1) + " Expected: False");

    }

    private static boolean isSameTree(TreeNode root_1, TreeNode root_2) {
        if (root_1 == null || root_2 == null) { // base case one: one of them is null
            return root_1 == root_2; // if they both are null, return true, if they are not both null, return false.
        }
        if (root_1.val != root_2.val) { // Base case 2: if their values are different
            return false;  // return false.
        }
        // recursively check left leaf and right leaf
        return isSameTree(root_1.left, root_2.left) && isSameTree(root_1.right, root_2.right);
    }
}
