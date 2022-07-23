package com.yc.algorithm.algorithm.leetcode;

/**
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 */
public class InvertTree_226 {
    public class TreeNode {
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


    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        //递归得到左右节点
        TreeNode leftNode = invertTree(root.left);
        TreeNode rightNode = invertTree(root.right);

        //交换左右节点
        root.left = rightNode;
        root.right = leftNode;
        return root;
    }
}
