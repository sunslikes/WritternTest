package top.sunslikes.test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 有重复序列的全排列
 */
public class FullArrage {
    public static void arrage(int[] ints) {
        arrage(ints, 0);
    }

    public static void arrage(int[] ints, int index) {
        if (index == ints.length) {
            System.out.println(Arrays.stream(ints).boxed().collect(Collectors.toList()));
            return;
        }
        // 跟前面的换
        for (int i = index; i >= 0; --i) {
            // 重复元素不用换位，剪枝
            if (i != index && ints[i] == ints[index]) {
                continue;
            }
            swap(ints, i, index);
            arrage(ints, index + 1);
            swap(ints, i, index);
        }
    }

    public static void swap(int[] ints, int x, int y) {
        int tmp = ints[x];
        ints[x] = ints[y];
        ints[y] = tmp;
    }

    public static void main(String[] args) {
        int[] ints = {1, 3, 3};
        arrage(ints);
    }
}
