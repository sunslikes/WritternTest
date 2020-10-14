package top.sunslikes.test;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 * 游游今年就要毕业了，和同学们在携程上定制了日本毕业旅行。愉快的一天行程结束后大家回到了酒店房间，这时候同学们都很口渴，石头剪刀布选出游游去楼下的自动贩卖机给大家买可乐。
 *
 * 贩卖机只支持硬币支付，且收退都只支持10 ，50，100 三种面额。一次购买行为只能出一瓶可乐，且每次购买后总是找零最小枚数的硬币。（例如投入100圆，可乐30圆，则找零50圆一枚，10圆两枚）
 *
 * 游游需要购买的可乐数量是 m，其中手头拥有的 10,50,100 面额硬币的枚数分别是 a,b,c，可乐的价格是x(x是10的倍数)。
 *
 * 如果游游优先使用大面额购买且钱是够的情况下,请计算出需要投入硬币次数？
 */
public class XieCheng1 {

    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int buyCoke(int m, int a, int b, int c, int x) {
        if (m == 0) {
            return 0;
        }
        int count = 0;
        int need = x;
        // 只投c
        if (c * 100 >= need) {
            // 每次只能一瓶一瓶买
            // 向上取整
            count = need / 100 + (need % 100 > 0? 1: 0);
            int money = count * 100 - need;
            c -= count;
            b += money / 50;
            money -= money / 50;
            a += money / 10;
        } else if (c * 100 + b * 50 >= need) {
            // 花光c
            count = c;
            need = need - c * 100;
            int bCount = need / 50 + (need % 50 > 0? 1: 0);
            count += bCount;
            int money = bCount * 50 - need;
            b -= bCount;
            a += money / 10;
        } else if (c * 100 + b * 50 + a * 10 >= x) {
            // 花光b, c
            count = c + b;
            need = need - c * 100 - b * 50;
            int aCount = need / 10;
            count += aCount;
            a -= aCount;
        } else {
            // 没钱了，返回0结束
            return 0;
        }
        return count + buyCoke(m - 1, a, b, c, x);
    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        int _m;
        _m = Integer.parseInt(in.nextLine().trim());

        int _a;
        _a = Integer.parseInt(in.nextLine().trim());

        int _b;
        _b = Integer.parseInt(in.nextLine().trim());

        int _c;
        _c = Integer.parseInt(in.nextLine().trim());

        int _x;
        _x = Integer.parseInt(in.nextLine().trim());

        res = buyCoke(_m, _a, _b, _c, _x);
        System.out.println(String.valueOf(res));
    }
}
