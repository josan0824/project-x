package com.yc.algorithm.algorithm.leetcode;

public class DiameterOfBinaryTree_543 {

    //[1,1,1]
    //[1]
    int maxd = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        //思路分析
        depth(root);
        return maxd;
    }

    //树深度
    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        //左边深度
        int Left = depth(node.left);
        //右边深度
        int Right = depth(node.right);
        //当前节点的最大直接为left+right, 再跟最大直径比较
        maxd = Math.max(Left + Right, maxd);
        //深度为左右最大深度+1
        return Math.max(Left, Right) + 1;
    }
}
