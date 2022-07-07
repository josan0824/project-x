package com.yc.algorithm.algorithm.commonly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: josan_tang
 * @create_date: 2022/7/7 9:47
 * @desc:树相关
 * @version:
 */
public class TreeTest {

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        Integer value;

        public TreeNode(Integer value) {
            this.value = value;
        }

        public TreeNode(TreeNode left, TreeNode right, Integer value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }


    public static void main(String[] args) {
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node4 = new TreeNode(node8 , node9,5);
        TreeNode node5 = new TreeNode(7);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(node4, node5, 4);
        TreeNode root = new TreeNode(node2, node3, 3);
        System.out.println(rob(root));
    }


    /**遍历***/

    /**
     * 前序遍历:按照访问根节点——左子树——右子树的方式遍历这棵树
     * 而在访问左子树或者右子树的时候，我们按照同样的方式遍历，直到遍历完整棵树
     * 1. 我们只要首先将 root 节点的值加入答案
     * 2. 然后递归调用 preorder(root.left) 来遍历 root 节点的左子树
     * 3. 最后递归调用 preorder(root.right) 来遍历 root 节点的右子树即可
     * 4. 递归终止的条件为碰到空节点
     * @param root
     * @return
     */
    public static List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    /**
     * 前序遍历
     * @param root
     * @param res
     */
    private static void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.value);
        preorder(root.left, res);
        preorder(root.right, res);
    }


    /**
     * 后序遍历:按照访问左子树——右子树——根节点的方式遍历这棵树
     * 而在访问左子树或者右子树的时候，我们按照同样的方式遍历，直到遍历完整棵树
     * 1. 我们只要首先递归调用 preorder(root.left) 来遍历 root 节点的左子树
     * 2. 然后递归调用 preorder(root.right) 来遍历 root 节点的右子树即可
     * 3. 最后将 root 节点的值加入答案
     * 4. 递归终止的条件为碰到空节点
     * @param root
     * @return
     */
    public static List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    /**
     * 后序遍历
     * @param root
     * @param res
     */
    private static void postorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.value);
    }

    /**
     * 给定一个二叉树，找出其最大深度。
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            //到叶子节点
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    /**
     * 110. 平衡二叉树
     * 是否是平衡树
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     *
     * 本题中，一棵高度平衡二叉树定义为：
     *
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return ((Math.abs(maxDepth(root.left) - maxDepth(root.right))) <= 1) && isBalanced(root.left) && isBalanced(root.right);
        }
    }


    //f存储选中对应的值包括节点自身的值；g存储未选中对应的
    static Map<TreeNode, Integer> f = new HashMap<>();
    static Map<TreeNode, Integer> g = new HashMap<>();

    /**
     * 337. 打家劫舍 III
     * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
     *
     * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
     *
     * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/house-robber-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public static int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    private static void dfs(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        dfs(treeNode.left);
        dfs(treeNode.right);
        f.put(treeNode, treeNode.value + g.getOrDefault(treeNode.left, 0) + g.getOrDefault(treeNode.right, 0));
        g.put(treeNode, Math.max(f.getOrDefault(treeNode.left, 0), g.getOrDefault(treeNode.left,0)) +
                Math.max(f.getOrDefault(treeNode.right, 0), g.getOrDefault(treeNode.right,0)));
    }


}
