package top.sunslikes.test;

import java.util.Scanner;

/**
 * @ClassName: Main1
 * @Description: TODO
 * 把m个同样的足球放进n个同样的篮子里，允许有的篮子为空，问共有几种分法？
 * 例如：3, 2, 1和2, 1, 3是同一种分法。
 * @Author: sunslikes
 * @Date: 2020/10/10 9:54
 * @Version: 1.0
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        System.out.println(f(m, n));
    }
    public static Integer f(int m, int n) {
        if (m == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n > m) {
            return f(m, m);
        }
        return f(m - n, n) + f(m, n - 1);
    }
}
