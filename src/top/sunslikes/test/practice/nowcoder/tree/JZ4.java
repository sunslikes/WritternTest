package top.sunslikes.test.practice.nowcoder.tree;


import com.sun.istack.internal.NotNull;

import java.util.Arrays;

/**
 * 重建二叉树s
 * 题目描述
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
 * 则重建二叉树并返回。
 */
public class JZ4 {
    /**
     * 这个稳得，仔细观察中序左边都是他的左子树，右边都是他的右子树，所以可以算出前序的
     * 递归
     * @param pre
     * @param in
     * @return
     */
    public static TreeNode reConstructBinaryTree1(int [] pre,int [] in) {
        if (pre == null) {
            return null;
        }
        TreeNode node = new TreeNode(pre[0]);
        if (pre.length == 1) {
            return node;
        }
        int middle = findIndex(in, 0, in.length, pre[0]);
        node.left = reConstructBinaryTree1(Arrays.copyOfRange(pre, 1, middle + 1),
                Arrays.copyOfRange(in, 0, middle));
        node.right = reConstructBinaryTree(Arrays.copyOfRange(pre, middle + 1, pre.length),
                Arrays.copyOfRange(in, middle + 1, in.length));
        return node;
    }

    /**
     * low一点的版本 ， 为啥设置这个，因为每次都是先完成左树，到值不是左树的结点时就会去搞右子树，就是递归往后找，看看是属于哪个右子树的
     */
    private static int rightIndex;
    /**
     * 数组中的数字都是不重复的
     * @param pre
     * @param in
     * @return
     */
    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length == 0) {
            return null;
        }
        TreeNode head = reConstructBinaryTree(pre, in, 0, in.length, 0);
        return head;
    }
    private static TreeNode reConstructBinaryTree(int[] pre, int[] in, int inStart, int inEnd, int preIndex) {
        // 说明没有子树了
        if (inStart > inEnd || preIndex > pre.length - 1) {
            return null;
        }
        int middle = findIndex(in, inStart, inEnd, pre[preIndex]);
        // 说明当前数值不属于这棵子树
        if (middle == -1) {
            return null;
        }
        TreeNode node = new TreeNode(pre[preIndex]);
        node.left = reConstructBinaryTree(pre, in, inStart, middle - 1, preIndex + 1);
        // 说明该preindex + 1属于某颗右子树
        if (node.left == null) {
            rightIndex = preIndex + 1;
        }
        node.right = reConstructBinaryTree(pre, in, middle + 1, inEnd, rightIndex);
        return node;
    }
    /**
     * 找到数字在哪个位置
     */
    private static int findIndex(int[] a, int start, int end, int value) {
        for (int i = start; i <= end; ++i) {
            if (a[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,8};
        int[] b = {3,2,5,4,6,7,1,8};
        TreeNode node = reConstructBinaryTree1(a, b);
        System.out.println(node);
    }
}
