package top.sunslikes.test.exam;

import java.util.*;

/**
 * 层序遍历
 */
public class ZiJie3 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(5), null);
        root.right = new TreeNode(3, new TreeNode(6), new TreeNode(7));
        // table a , b, c (a,b,c)
        // select * from table where a > 1 and b like "%123" and c > 1
        List<TreeNode> nodeList = new ArrayList<>();
        // 加入树节点
        nodeList.add(root);
        printOne(nodeList);
    }
    private static void printOne(List<TreeNode> nodeList) {
        if (nodeList.size() == 0) {
            return;
        }
        List<TreeNode> next = new ArrayList<>();
        for (int i = 0; i < nodeList.size(); i++) {
            TreeNode node = nodeList.get(i);
            System.out.print(node.val + " ");
            if (node.left != null) {
                next.add(node.left);
            }
            if (node.right != null) {
                next.add(node.right);
            }
        }
        System.out.println();
        printOne(next);
    }
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }
}
