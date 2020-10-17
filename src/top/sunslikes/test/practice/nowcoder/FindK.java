package top.sunslikes.test.practice.nowcoder;

/**
 * 寻找第K大
 * 题目描述
 * 有一个整数数组，请你根据快速排序的思路，找出数组中第K大的数。
 *
 * 给定一个整数数组a,同时给定它的大小n和要找的K(K在1到n之间)，请返回第K大的数，保证答案存在。
 *
 * 测试样例：
 * [1,3,5,2,2],5,3
 * 返回：2
 */
public class FindK {
    public int findKth(int[] a, int n, int K) {
        return findKth(a, 0, n - 1, K);
    }
    // 从大往小排
    public static int quickSortOnce(int[] a, int start, int end) {

        int plv = a[start];
        int i = start;
        int j = end;
        while(i < j) {
            while (i < j && a[j] <= plv) {
                j--;
            }
            a[i] = a[j];
            while (i < j && a[i] >= plv) {
                i++;
            }
            a[j] = a[i];
        }
        a[i] = plv;
        return i;
    }
    // 快排+二分
    public static int findKth(int[] a, int start, int end, int K) {
        if (start > end) {
            return -1;
        }
        int middle = quickSortOnce(a, start, end);
        // 二分，看看i小于k还是大于, 等于就直接返回结果
        if (middle == K - 1) {
            return a[middle];
        } else if (middle > K - 1) {
            return findKth(a, start, middle - 1, K);
        } else {
            return findKth(a, middle + 1, end, K);
        }
    }

    public static void main(String[] args) {
        int[] a = {1332802,1177178,1514891,871248,753214,123866,1615405,328656,1540395,968891,1884022,252932,1034406,1455178,821713,486232,860175,1896237,852300,566715,1285209,1845742,883142,259266,520911,1844960,218188,1528217,332380,261485,1111670,16920,1249664,1199799,1959818,1546744,1904944,51047,1176397,190970,48715,349690,673887,1648782,1010556,1165786,937247,986578,798663};
        System.out.println(a.length);
        System.out.println(new FindK().findKth(a, a.length, 24));
    }
}
