package src.Tree;

import java.util.LinkedList;
import java.util.Queue;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class MyRecurSolution {
    public MyRecurSolution() {
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return compareSubtree(root.left, root.right);
    }

    public boolean compareSubtree(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if ((node1 == null && node2 != null) || (node1 != null && node2 == null)) {
            return false;
        }
        if (node1.val != node2.val) {
            return false;
        }
        return compareSubtree(node1.left, node2.right) && compareSubtree(node1.right, node2.left);
    }
}

class MyQueueSolution {
    public MyQueueSolution() {
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root.left);
        que.offer(root.right);

        while (!que.isEmpty()) {
            TreeNode left = que.poll();
            TreeNode right = que.poll();
            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;

            // enque outter two
            que.offer(left.left);
            que.offer(right.right);
            // enque inner two
            que.offer(left.right);
            que.offer(right.left);
        }
        return true;
    }
}

public class SymmetricTree_101 {
    public static void main(String[] args) {
        TreeNode ll = new TreeNode(3);
        TreeNode lr = new TreeNode(4);
        TreeNode l = new TreeNode(2, ll, lr);
        TreeNode rl = new TreeNode(4);
        TreeNode rr = new TreeNode(3);
        TreeNode r = new TreeNode(2, rl, rr);
        TreeNode root = new TreeNode(1, l, r);

        MyRecurSolution solve1 = new MyRecurSolution();
        System.out.println("Result of Recursive solution: " + solve1.isSymmetric(root));

        MyQueueSolution solve2 = new MyQueueSolution();
        System.out.println("Result of Queue solution: " + solve2.isSymmetric(root));
    }
}
