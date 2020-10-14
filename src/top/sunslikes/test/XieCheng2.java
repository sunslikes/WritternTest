package top.sunslikes.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 * 正值下班高峰时期，现有可载客司机数2N人，调度中心将调度相关司机服务A、B两个出行高峰区域。
 *
 * 第 i 个司机前往服务A区域可得收入为 income[i][0]，前往服务B区域可得收入为 income[i][1]。
 *
 * 返回将每位司机调度完成服务后，所有司机总可得的最高收入金额，要求每个区域都有 N 位司机服务。
 */
public class XieCheng2 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        boolean flag = true;
        List<Integer> moneys = new ArrayList<>();
        while (cin.hasNextInt()) {
            try {
                int incomeOfA = cin.nextInt();
                int incomeOfB = cin.nextInt();
                //Start coding -- Input Data
                if (incomeOfA < 0|| incomeOfB < 0) {
                    System.out.println("error");
                    flag = false;
                    break;
                }
                moneys.add(Math.max(incomeOfA, incomeOfB));
            } catch (Exception e) {
                System.out.println("error");
                flag = false;
                break;
            }
        }
        //Start coding
        if (flag) {
            int sum = 0;
            for (Integer money : moneys) {
                sum += money;
            }
            System.out.println(sum);
        }
    }
}
