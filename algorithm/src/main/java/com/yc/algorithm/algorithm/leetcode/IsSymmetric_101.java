package com.yc.algorithm.algorithm.leetcode;

/**
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称
 */
public class IsSymmetric_101 {
    public boolean isSymmetric(TreeNode root) {
        //check params
        //树的节点数满足1 <= n <= 100要求
        return check(root, root);
    }

    private boolean check(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) {
            //如果两个都为null,在证明之前没有不相同的，已经找到了最下层的元素
            return true;
        }

        if (leftNode == null || rightNode == null) {
            //有一个不为空一个为空，则肯定不对称
            return false;
        }

        //如果左右两个根节点相同，且左右两个节点也是对称的
        return leftNode.val == rightNode.val && check(leftNode.left, rightNode.right)
                && check(leftNode.right, rightNode.left);
    }
}
