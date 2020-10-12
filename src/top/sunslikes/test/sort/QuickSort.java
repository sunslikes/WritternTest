package top.sunslikes.test.sort;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 快排
 */
public class QuickSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String getString = scanner.nextLine();
        List<Integer> integers = Arrays.stream(getString.split(" "))
                                    .map(Integer::parseInt).collect(Collectors.toList());
        int[] ints = integers.stream().mapToInt(Integer::valueOf).toArray();
        sort(ints, 0, ints.length - 1);
        System.out.println(Arrays.stream(ints).boxed().collect(Collectors.toList()));
    }
    // 选取第一个
    public static void sort(int[] ints, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int symbol = ints[begin];
        int i = begin, j = end;
        for (; i < j; ) {
            // 找到比标志位小的放到左边
            for (; j > i; j--) {
                if (ints[j] < symbol) {
                    swap(ints, i, j);
                    break;
                }
            }
            // 找到比标志位大的放到右边
            for (; i < j; i++) {
                if (ints[i] > symbol) {
                    swap(ints, i, j);
                    break;
                }
            }
        }
        ints[i] = symbol;
        sort(ints, 0, i - 1);
        sort(ints, i + 1, end);
    }

    public static void swap(int[] ints, int x, int y) {
        int tmp = ints[x];
        ints[x] = ints[y];
        ints[y] = tmp;
    }
}
