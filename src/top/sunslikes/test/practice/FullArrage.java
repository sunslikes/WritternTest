package top.sunslikes.test.practice;

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
        // 跟后面的换
        for (int i = index; i < ints.length; ++i) {
            if (!isEqual(ints, index, i)) {
                swap(ints, i, index);
                arrage(ints, index + 1);
                swap(ints, i, index);
            }
        }
    }

    /**
     *对于 1abc2xyz2 这样的排列，我们交换1与第一个2，变成2abc1xyz2，按照递归的顺序，接下来对abc1xyz2进行全排列；但是1是不能和第二个2交换的，如果交换了，变成了2abc2xyz1，按照递归的顺序，接下来对abc2xyz1进行全排列，那么问题来了，注意我红色突出的两个地方，这两个全排列进行的都是同样的工作，也就是如果1和第二个2交换必然会和前面重复。
     * @param ints
     * @param begin
     * @param end
     * @return
     */
    public static boolean isEqual(int[] ints, int begin, int end) {
        for (int i = begin; i < end; ++i) {
            if (ints[i] == ints[end]) {
                return true;
            }
        }
        return false;
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
