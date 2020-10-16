package top.sunslikes.test.practice;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * n皇后  回溯法经典例题
 */
public class NQueen {
    public static int count = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] index = new int[n];
        fun(index, 0);
        System.out.println(count);
    }

    /**
     * 方程
     * y坐标确定
     * @param index 当前皇后的位置x[]
     * @param now 当前在操作第几个皇后
     */
    public static void fun(int[] index, int now) {
        if (now == index.length) {
//            System.out.println(Arrays.stream(index).boxed().collect(Collectors.toList()));
            count++;
            return;
        }
        // 当前所有可能位置
        for (int i = 0; i < index.length; i++) {
            boolean flag = true;
            // 判断这个位置行不行  剪枝
            for (int j = 0; j < now; j++) {
                int tmp = index[j];
                if (i == tmp || now - j == i - tmp || now - j == tmp - i) {
                    // 不符合条件
                    flag = false;
                    break;
                }
            }
            if (flag) {
                // 把当前位设置为i
                index[now] = i;
                fun(index, now + 1);
            }
        }
    }

}
