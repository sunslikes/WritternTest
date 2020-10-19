package top.sunslikes.test.practice.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName: LC102
 * @Description: TODO
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 * 通过次数206,735提交次数325,325
 * @Author: sunslikes
 * @Date: 2020/10/19 9:30
 * @Version: 1.0
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/**
 * 想了两种
 * 一种是 深度遍历，但是用list实现层序输出
 * 一种是 用队列实现广度遍历
 */
public class LC102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        return deepLevelOrder(root);
    }

    /**
     * 深度遍历
     * @param root
     * @return
     */
    public static List<List<Integer>> deepLevelOrder(TreeNode root) {
        List<List<Integer>> orders = new ArrayList<>();
        deepLevelOrder(root, orders, 0);
        return orders;
    }
    private static void deepLevelOrder(TreeNode node, List<List<Integer>> orders,
                                int deepth) {
        if (node == null) {
            return;
        }
        // 加进第n层的list
        List<Integer> integers;
        if (deepth < orders.size()) {
            integers = orders.get(deepth);
        } else {
            orders.add((integers = new ArrayList<>()));
        }
        integers.add(node.val);
        deepLevelOrder(node.left, orders, deepth + 1);
        deepLevelOrder(node.right, orders, deepth + 1);
    }

    /**
     * 广度遍历
     * @param root
     * @return
     */
    public static List<List<Integer>> breadLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        List<TreeNode> levelNodes = new ArrayList<>(Collections.nCopies(1, root));
        List<Integer> integers;
        do {
            integers = breadLevelOrderOne(levelNodes);
            if (integers.size() == 0) {
                break;
            }
            lists.add(integers);
        } while (true);
        return lists;
    }
    /**
     * 用上一层的list
     * @param treeNodes
     * @return
     */
    public static List<Integer> breadLevelOrderOne(List<TreeNode> treeNodes) {
        List<TreeNode> tmp = new ArrayList<>(treeNodes);
        // 清空
        treeNodes.clear();
        TreeNode node;
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < tmp.size(); ++i) {
            node = tmp.get(i);
            if (node == null) {
                continue;
            }
            integers.add(node.val);
            treeNodes.add(node.left);
            treeNodes.add(node.right);
        }
        return integers;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
        public static TreeNode listToTree(Integer[] a) {
            return listToTree(a, 0);
        }

        /**
         * @param a
         * @param i
         * @return
         */
        private static TreeNode listToTree(Integer[] a, int i) {
            if (i > a.length - 1 || a[i] == null) {
                return null;
            }
            TreeNode treeNode = new TreeNode(a[i]);
            treeNode.left = listToTree(a, 2 * i + 1);
            treeNode.right = listToTree(a, 2 * i + 2);
            return treeNode;
        }
    }

    public static void main(String[] args) {
        Integer[] a = {3,9,20,null,null,15,7};
        TreeNode treeNode = TreeNode.listToTree(a);
        System.out.println(LC102.deepLevelOrder(treeNode));
        System.out.println(LC102.breadLevelOrder(treeNode));
    }
}
