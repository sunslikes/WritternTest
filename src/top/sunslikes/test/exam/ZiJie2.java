package top.sunslikes.test.exam;

import java.util.Scanner;
import java.util.*;

/**
 * 给个数组，求最大和的连续子数组
 */
public class ZiJie2 {
    // 有个数组，找连续子数组 和是最大的
    public static void main(String[] args) {
        int[] number = {1,2,3,4,5,8,-9,10,-11};
        // 维护一个状态 0是当前值， 1是当前长度
        int[][] now = new int[number.length][2];
        now[0][0] = number[0];
        now[0][1] = 1;
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i = 1; i < number.length; i++) {
            // 前一个状态是小于0的
            if (now[i - 1][0] < 0) {
                now[i][0] = number[i];
                now[i][1] = 1;
            } else {
                // 符合条件，加进去
                now[i][0] = now[i - 1][0] + number[i];
                now[i][1] = now[i - 1][1] + 1;
            }
        }
        int index = 0, length = now[0][1];
        for (int i = 0; i < number.length; i++) {
            if (now[i][0] > now[index][0]) {
                index = i;
                length = now[i][1];
            }
        }
        int begin = index - length + 1;
        for (int i = begin; i <= index; i++) {
            ints.add(number[i]);
        }
        ints.forEach(System.out::println);
    }
}
