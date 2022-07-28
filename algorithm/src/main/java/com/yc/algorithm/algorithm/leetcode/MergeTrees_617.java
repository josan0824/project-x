package com.yc.algorithm.algorithm.leetcode;


 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }

public class MergeTrees_617 {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        //参数检查，题意已给出

        //思路分析
        //可用递归实现
        // 如果两个节点都没有，则返回null；
        // 如果只有一个节点，以这个节点为准；
        // 如果有两个节点，则以两个节点相加为准
        TreeNode leftNode = null;
        TreeNode rightNode = null;
        if (root1 == null && root2 == null) {
            return null;
        } else if (root1 == null){
            leftNode = mergeTrees(root2.left, null);
            rightNode = mergeTrees(root2.right, null);
            return root2;
        } else if (root2 == null){
            leftNode = mergeTrees(root1.left, null);
            rightNode = mergeTrees(root1.right, null);
            return root1;
        } else {
            leftNode = mergeTrees(root1.left, root2.left);
            rightNode = mergeTrees(root1.right, root2.right);
            return new TreeNode(root1.val + root2.val, leftNode, rightNode);
        }
    }

}
