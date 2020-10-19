package top.sunslikes.test.practice.nowcoder;

/*
 * public class TreeNode {
 *   int val = 0;
 *   TreeNode left = null;
 *   TreeNode right = null;
 * }
 */

import java.util.ArrayList;

/**
 * 题目描述
 * 分别按照二叉树先序，中序和后序打印所有的节点。
 * 示例1
 * 输入
 * 复制
 * {1,2,3}
 * 输出
 * 复制
 * [[1,2,3],[2,1,3],[2,3,1]]
 *
 * 超时不知道怎么解决
 */
public class NC45 {
    /**
     *
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders (TreeNode root) {
        // write code here
        ArrayList<Integer> integers = new ArrayList<>();
        preOrders(integers, root);
        int[][] allOrders = new int[3][integers.size()];
        allOrders[0] = integers.stream().mapToInt(Integer::intValue).toArray();
        integers = new ArrayList<>();
        midOrders(integers, root);
        allOrders[1] = integers.stream().mapToInt(Integer::intValue).toArray();
        integers = new ArrayList<>();
        postOrders(integers, root);
        allOrders[2] = integers.stream().mapToInt(Integer::intValue).toArray();
        return allOrders;
    }

    /**
     * 前序遍历
     * @param integers
     * @param node
     */
    public static void preOrders(ArrayList<Integer> integers, TreeNode node) {
        if (node == null) {
            return;
        }
        integers.add(node.val);
        preOrders(integers, node.left);
        preOrders(integers, node.right);
    }

    /**
     * 中序遍历
     * @param integers
     * @param node
     */
    public static void midOrders(ArrayList<Integer> integers, TreeNode node) {
        if (node == null) {
            return;
        }
        midOrders(integers, node.left);
        integers.add(node.val);
        midOrders(integers, node.right);
    }

    /**
     * 后序遍历
     * @param integers
     * @param node
     */
    public static void postOrders(ArrayList<Integer> integers, TreeNode node) {
        if (node == null) {
            return;
        }
        postOrders(integers, node.left);
        postOrders(integers, node.right);
        integers.add(node.val);
    }

    public static void main(String[] args) {
        new NC45().threeOrders(
                new TreeNode(1,
                        new TreeNode(2, null, null),
                        new TreeNode(3, null, null))
        );
    }
}
