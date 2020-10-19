package top.sunslikes.test.practice.nowcoder;

/**
 * @ClassName: NC105
 * @Description: TODO
 * 题目描述
 * 请实现有重复数字的有序数组的二分查找。
 * 输出在数组中第一个大于等于查找值的位置，如果数组中不存在这样的数，则输出数组长度加一。
 * 示例1
 * 输入
 * 复制
 * 5,4,[1,2,4,4,5]
 * 输出
 * 复制
 * 3
 * @Author: sunslikes
 * @Date: 2020/10/19 16:52
 * @Version: 1.0
 */
public class NC105 {
    /**
     * 二分查找
     * 这样的话就算找到了值，也要往前推有没有更小的
     * @param n int整型 数组长度
     * @param v int整型 查找值
     * @param a int整型一维数组 有序数组
     * @return int整型
     */
    public int upper_bound_ (int n, int v, int[] a) {
        // write code here
        int middle = bFing(a, 0, n - 1, v);
        for (int i = middle - 1; i >= 0 && i < a.length; --i) {
            if (a[i] == v) {
                middle = i;
                continue;
            }
            break;
        }
        return middle + 1;
    }
    private static int bFing(int[] ints, int start, int end, int key) {
        if (start > end || start >= ints.length) {
            return start;
        }
        // 向上取整, 找到中间的值
        int middle = (int) Math.ceil((end + start) / 2.0);
        int result = ints[middle];
        if (result == key) {
            return middle;
        } else if (result > key) {
            return bFing(ints, start, middle - 1, key);
        } else {
            return bFing(ints, middle + 1, end, key);
        }
    }

    public static void main(String[] args) {
        int[] a = {2,3,3,4,4,5,5,5,6,6,7,7,9,9,9,10,10,12,14,16,17,18,18,18,
                19,22,23,23,26,26,26,26,28,28,29,29,29,32,33,35,36,38,39,39,40,41,46,47,47,47,49,50,54,55,55,55,56,57,57,58,58,58,59,61,61,62,62,62,62,63,63,67,67,69,70,70,71,72,74,75,76,79,84,85,85,86,89,92,93,93,93,94,94,95,97,97,97,97,97,99};
        System.out.println(new NC105().upper_bound_(100, 100, a));
    }
}
