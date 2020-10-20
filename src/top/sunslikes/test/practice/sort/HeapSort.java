package top.sunslikes.test.practice.sort;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 大小顶堆都是一个思路，理解了堆的概念就好做了
 */
public class HeapSort {
    public static void heapSort(int[] a) {
        List<Integer> integers = new ArrayList<>();
        createBigHeap(a);
        for (int i = 0; i < a.length; ++i) {
            adjustBigHeap(a, i);
        }
    }

    /**
     * 创建初始大顶堆
     * @param a
     */
    private static void createBigHeap(int[] a) {
        // 从a.length/2开始从后往前，我们知道a_i的左结点是a_2i+1右是a_2i+2
        // 必须子树整齐之后往上整合才能整齐
        for (int i = (a.length - 1) / 2; i >= 0; --i) {
            // 奇数是左子树，偶数是右子树，构建大数做爸爸
            int left = 2 * i + 1;
            if (left < a.length && a[left] > a[i]) {
                swap(a, i, left);
            }
            int right = 2 * i + 2;
            if (right < a.length && a[right] > a[i]) {
                swap(a, i, right);
            }
        }
    }

    /**
     * 我直接把堆顶的值扔堆尾，这样大顶堆出来的就会是从小到大排序
     * @param a
     * @return
     */
    private static void adjustBigHeap(int[] a, int i) {
        // 当前堆的最后一位
        int last = a.length - 1 - i;
        swap(a, 0, last);
        for (int j = 0; j < last; ) {
            int left = 2 * j + 1;
            int right = 2 * j + 2;
            // 找到三个中最大的值
            if (left < last && right < last) {
                int k = a[left] > a[right]? left: right;
                k = a[k] > a[j]? k: j;
                // k == j说明换完了
                if (k == j) {
                    break;
                }
                swap(a, j, k);
                j = k;
            } else if (left < last) {
                int k = a[left] > a[j]? left: j;
                // k == j说明换完了
                if (k == j) {
                    break;
                }
                swap(a, j, k);
                j = k;
            } else if (right < last) {
                int k = a[right] > a[j]? right: j;
                // k == j说明换完了
                if (k == j) {
                    break;
                }
                swap(a, j, k);
                j = k;
            } else {
                break;
            }
        }
    }

    private static void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    public static void main(String[] args) {
        int[] a = {4, 3, 1, 4, 6, 2};
        heapSort(a);
        System.out.println(Arrays.stream(a).boxed().collect(Collectors.toList()));
    }
}
