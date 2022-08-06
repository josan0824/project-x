package com.yc.algorithm.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Flatten_114 {
    public static void main(String[] args) {
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(5, null, treeNode6);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2, treeNode3, treeNode4);
        TreeNode treeNode1 = new TreeNode(1, treeNode2, treeNode5);

        new Flatten_114().flatten(treeNode1);

    }

    public void flatten(TreeNode root) {
        List<TreeNode> treeNodeList = getFirstTree(root);
        if (treeNodeList != null) {
            TreeNode pre = treeNodeList.get(0);
            root = pre;
            root.left = null;
            for (int i = 1; i < treeNodeList.size(); i ++) {
                pre.right = treeNodeList.get(i);
                pre.left = null;
                pre = treeNodeList.get(i);
            }
        }
    }

    /**
     * 前序遍历
     * @param root
     * @return
     */
    private List<TreeNode> getFirstTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<TreeNode> resultList = new ArrayList<>();
        resultList.add(root);
        List<TreeNode> leftNodeList = getFirstTree(root.left);
        if (leftNodeList != null) {
            resultList.addAll(leftNodeList);
        }
        List<TreeNode> rightNodeList = getFirstTree(root.right);
        if (rightNodeList != null) {
            resultList.addAll(rightNodeList);
        }
        return resultList;
    }

}
