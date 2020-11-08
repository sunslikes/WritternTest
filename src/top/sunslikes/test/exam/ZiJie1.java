package top.sunslikes.test.exam;
import java.util.*;

/**
 * 给定一棵二叉树和一个正整数，其中二叉树上每个结点都有一个正整数。求一条从根结点开始的路径，使得路径的和为给定的正整数
 */
public class ZiJie1 {
    public static List<TreeNode> nodeList = new ArrayList<>();
    //二叉树，结点正整数， x, 从根节点开始，找一条路径，所有结点的和要是给定的x
    public void get(TreeNode root, int x) {
        if(!get(root, x, 0)) {
            nodeList.remove(root);
        }
    }
    public boolean get(TreeNode root, int x, int now) {
        boolean flag = false;
        now = now + root.val;
        nodeList.add(root);
        if (now > x) {
            return false;
        } else if (now == x) {
            return true;
        }
        if (root.left != null) {
            flag = get(root.left, x, now);
            if (!flag) {
                nodeList.remove(root.left);
            } else {
                return true;
            }
        }
        if (root.right != null) {
            flag = get(root.right, x, now);
            if (!flag) {
                nodeList.remove(root.right);
            } else {
                return true;
            }
        }
        return false;
    }
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        ZiJie1 ziJie1 = new ZiJie1();
        ziJie1.get(root, 2);
        System.out.println(nodeList);
    }
}
