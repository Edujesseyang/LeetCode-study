package src.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal_94 {
    /*
    Given the root of a binary tree, return the inorder traversal of its nodes' values.



Example 1:

Input: root = [1,null,2,3]

Output: [1,3,2]

Explanation:



Example 2:

Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]

Output: [4,2,6,5,7,1,3,9,8]

Explanation:



Example 3:

Input: root = []

Output: []

Example 4:

Input: root = [1]

Output: [1]



Constraints:

The number of nodes in the tree is in the range [0, 100].
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
        // 测试栈法:
        System.out.println("******** Test stack approach ********");
        // Test 1: 空树 (empty tree)
        System.out.println(inorderTraversal(null)); // 输出：[]

        // Test 2: 单节点 (single node)
        TreeNode t1 = new TreeNode(1, null, null);
        System.out.println(inorderTraversal(t1)); // 输出：[1]

        // Test 3: 完整二叉树 (balanced binary tree)
        TreeNode t3 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4, null, null),
                        new TreeNode(5, null, null)),
                new TreeNode(3, null, null));
        System.out.println(inorderTraversal(t3)); // 输出：[4, 2, 5, 1, 3]

        // Test 4: 不平衡左侧树 (left skewed)
        TreeNode t4 = new TreeNode(3,
                new TreeNode(2,
                        new TreeNode(1, null, null),
                        null),
                null);
        System.out.println(inorderTraversal(t4)); // 输出：[1, 2, 3]

        // Test 5: 不平衡右侧树 (right skewed)
        TreeNode t5 = new TreeNode(1, null,
                new TreeNode(2, null,
                        new TreeNode(3, null, null)));
        System.out.println(inorderTraversal(t5)); // 输出：[1, 2, 3]

        // 测试递归法
        System.out.println("******** Test recursion approach ********");
        // Test 1: 空树 (empty tree)
        System.out.println(inorderTraverseRecursive(null)); // 输出：[]

        // Test 2: 单节点 (single node)
        TreeNode ts1 = new TreeNode(1, null, null);
        System.out.println(inorderTraverseRecursive(ts1)); // 输出：[1]

        // Test 3: 完整二叉树 (balanced binary tree)
        TreeNode ts3 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4, null, null),
                        new TreeNode(5, null, null)),
                new TreeNode(3, null, null));
        System.out.println(inorderTraverseRecursive(ts3)); // 输出：[4, 2, 5, 1, 3]

        // Test 4: 不平衡左侧树 (left skewed)
        TreeNode ts4 = new TreeNode(3,
                new TreeNode(2,
                        new TreeNode(1, null, null),
                        null),
                null);
        System.out.println(inorderTraverseRecursive(ts4)); // 输出：[1, 2, 3]

        // Test 5: 不平衡右侧树 (right skewed)
        TreeNode ts5 = new TreeNode(1, null,
                new TreeNode(2, null,
                        new TreeNode(3, null, null)));
        System.out.println(inorderTraverseRecursive(ts5)); // 输出：[1, 2, 3]
    }


    // 用递归来实现.
    private static List<Integer> inorderTraverseRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    private static void traverse(TreeNode current, List<Integer> result) {
        if (current == null) {
            return;
        }
        traverse(current.left, result);
        result.add(current.val);
        traverse(current.right, result);
    }


    // 用stack来实现
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            ans.add(current.val);
            current = current.right;
        }
        return ans;
    }
}
