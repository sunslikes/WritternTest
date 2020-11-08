package top.sunslikes.test.practice;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RBTree {

    TreeNode root = null;

    public boolean putVal(int i) {
        // 新建树
        if (root == null) {
            root = new TreeNode(i);
            root.red = false;
        } else {
            TreeNode now = root, parent = null;
            TreeNode node = new TreeNode(i);
            while (now != null) {
                // 有这样的值无需再插入
                if (i == now.val) {
                    return false;
                } else if (i < now.val) {
                    parent = now;
                    now = now.left;
                } else {
                    parent = now;
                    now = now.right;
                }
            }
            node.parent = parent;
            if (i < parent.val) {
                parent.left = node;
            } else {
                parent.right = node;
            }
            root = balanceInsertion(root, node);
        }
        System.out.println("=====" + i + "=====");
        breadLevelOrder(root);
        return true;
    }

    public boolean delectNode(int i) {
        // p是要删除的结点， k是要替换的结点
        TreeNode now = root, p = null, k = null;
        while (now != null) {
            if (now.val == i) {
                p = now;
                break;
            } else if (i < now.val) {
                now = now.left;
            } else {
                now = now.right;
            }
        }
        if (p == null) {
            return false;
        }
        // 找左子树的最右结点
        k = now.left;
        if (k != null) {
            while (k.right != null) {
                k = k.right;
            }
        } else {
            k = p;
        }
        root = swichNodes(root, p, k);
        root = balanceDeletion(root, k);
        return true;
    }

    private static TreeNode swichNodes(TreeNode root, TreeNode p, TreeNode k) {
        // 交换k、p
        TreeNode tmp = new TreeNode(k.val), pp, kp;
        tmp.parent = k.parent;
        tmp.left = k.left;
        tmp.right = k.right;
        tmp.red = k.red;
        k.val = p.val;
        if ((pp = k.parent = p.parent) != null) {
            if (pp.left == p) {
                pp.left = k;
            } else {
                pp.right = k;
            }
        }
        k.red = p.red;
        k.left = p.left;
        k.right = p.right;
        p.val = tmp.val;
        if ((kp = p.parent = tmp.parent) != null) {
            if (kp.left == tmp) {
                kp.left = p;
            } else {
                kp.right = p;
            }
        }
        p.right = tmp.right;
        p.left = tmp.left;
        p.red = tmp.red;
        return root;
    }

    /**
     * 平衡加删除
     * @param root
     * @param x
     * @return
     */
    private static TreeNode balanceDeletion(TreeNode root, TreeNode k) {
        // 如果删除结点是红色的就什么都不用做了
        if (!k.red) {

        }
        TreeNode kp = k.parent, kl = k.left;
        if (kl != null) {
            kl.parent = kp;
        }
        if (kp != null) {
            if (kp.left == k) {
                kp.left = kl;
            } else {
                kp.right = kl;
            }
        }
        // 如果是null 那说明删的是根节点
        else {
            kl.red = false;
            return kl;
        }
        return null;
    }
    /**
     * 平衡插入之后的红黑树
     * 参考2-3-4树进行总结
     * @param x
     * @return 根节点
     */
    private static TreeNode balanceInsertion(TreeNode root, TreeNode x) {
        for (TreeNode xp, xpp, xppr, xppl; ;) {
            // 父节点是空，说明是根
            if ((xp = x.parent) == null) {
                x.red = false;
                return x;
            }
            // （插入2-节点）父节点是黑色的直接插入即可; 父节点是根，直接插入即可(其实这里主要是为了拿xpp，因为根就是黑的
            else if (!xp.red || (xpp = xp.parent) == null) {
                return root;
            }
            // 父节点是祖父节点的左节点
            if ((xppl = xpp.left) == xp) {
                // 看看插入的是不是4-节点（2-3-4
                if ((xppr = xpp.right) != null && xppr.red) {
                    xpp.red = true;
                    xppl.red = false;
                    xppr.red = false;
                    // 祖父节点变红往上继续平衡
                    x = xpp;
                }
                // 插入3-节点
                else {
                    // 这种是左 右情况，要先左旋
                    if (x == xp.right) {
                        // 为啥要x = xp？因为接下来右旋的都是刚刚xpp，保证不会选错
                        root = rotateLeft(root, x = xp);
                        xpp = (xp = x.parent) == null? null: xp.parent;
                    }
                    // 这种是全部在左的情况，右旋xpp
                    if (xp != null) {
                        xp.red = false;
                        if (xpp != null) {
                            xpp.red = true;
                            root = rotateRight(root, xpp);
                        }
                    }
                }
            }
            else {
                // 4-
                if (xppl != null && xppl.red) {
                    xpp.red = true;
                    xpp.right.red = false;
                    xpp.left.red = false;
                    x = xpp;
                }
                // 插入3-
                else {
                    // 右 左
                    if (xp.left == x) {
                        root = rotateRight(root, x = xp);
                        xpp = (xp = x.parent) == null? null: xp.parent;
                    }
                    if (xp != null) {
                        xp.red = false;
                        if (xpp != null) {
                            xpp.red = true;
                            root = rotateLeft(root, xpp);
                        }
                    }
                }
            }
        }
    }

    /**
     * 左旋
     * @param root
     * @param x
     * @return
     */
    private static TreeNode rotateLeft(TreeNode root, TreeNode x) {
        TreeNode r, xp, rl;
        if (x != null && (r = x.right) != null) {
            if ((rl = x.right = r.left) != null) {
                rl.parent = x;
            }
            // 根节点
            if ((xp = r.parent = x.parent) == null) {
                (root = r).red = false;
            } else if (xp.left == x) {
                xp.left = r;
            } else {
                xp.right = r;
            }
            r.left = x;
            x.parent = r;
        }
        return root;
    }

    /**
     * 右旋
     * @param root
     * @param x
     * @return
     */
    private static TreeNode rotateRight(TreeNode root, TreeNode x) {
        TreeNode l, xp, lr;
        if (x != null && (l = x.left) != null) {
            if ((lr = x.left = l.right) != null) {
                lr.parent = x;
            }
            if ((xp = l.parent = x.parent) == null) {
                (root = l).red = false;
            } else if (xp.left == x) {
                xp.left = l;
            } else {
                xp.right = l;
            }
            l.right = x;
            x.parent = l;
        }
        return root;
    }


    private static class TreeNode {
        Integer val;
        TreeNode parent;
        TreeNode left;
        TreeNode right;
        boolean red;

        // 初始化插入红结点
        public TreeNode(Integer val) {
            this.val = val;
            parent = null;
            left = null;
            right = null;
            red = true;
        }

        @Override
        public String toString() {
            return "[" + (parent!=null?parent.val:"") + "|" + val + ":" + (red? "R":"B") + "]";
        }
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
        System.out.println(treeNodes);
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



    public static void main(String[] args) {
        RBTree rbTree = new RBTree();
        rbTree.putVal(9);
        rbTree.putVal(7);
        rbTree.putVal(10);
        rbTree.putVal(8);
        rbTree.putVal(5);
        rbTree.putVal(4);
        rbTree.putVal(6);
        rbTree.putVal(2);
        breadLevelOrder(rbTree.root).forEach(System.out::println);
    }
}
