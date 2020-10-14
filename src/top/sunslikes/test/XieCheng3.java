package top.sunslikes.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 * 作为一个新手程序员，数组越界是一个非常容易犯的错误。游游为了提醒自己，决定写一个小程序来分析自己的代码中是否存在这样的错误。但它很快发现，这项工作对于它来说太过困难了。所以它希望你来帮忙实现一个简化后的版本。
 *
 * 在这个简化后的版本中，它所需要处理的语句只有以下两种类型：
 *
 * 1. 整形数组定义语句：格式为name[size]。例如：a[5]或者array[10]。数组可用的下标为[0, size)。定义后的数组所有的元素均为0；
 *
 * 2. 赋值语句：格式为name[index]=value。例如：a[0]=1或者a[a[0]]=a[a[3]]。
 *
 * 在被分析的代码中，数组越界错误只会出现在赋值语句中，且代码中只会有这一类错误。游游希望你帮它找出代码中第一次出现错误的语句，并输出对应的行号。
 */
public class XieCheng3 {
    // 数组
    private static final HashMap<String, ArrayList<Integer>> arrayListHashMap = new HashMap<>();
    private static Pattern pattern = Pattern.compile("[0,9]+");


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int validateArrayUsages(String[] lines) {
        for (String line : lines) {
            String[] fuzhi = line.split("=");
            // 说明是定义
            if (fuzhi.length < 2) {
                String[] dingyi = line.split("\\[");
                Integer length = Integer.valueOf(dingyi[1].substring(0, dingyi[1].length() - 1));
                // 用0填满
                arrayListHashMap.put(dingyi[0], new ArrayList<>(Collections.nCopies(length, 0)));
                continue;
            }

        }
        return 0;
    }
    // 判断赋值语句
    private boolean judge(String line) {
        if (calculate(line) == null) {
            return false;
        }
        return true;
    }
    // 计算a[0]=1
    private Integer calculate(String line) {
        // 先默认都是0吧。。。实际赋值就不做先
        String[]
    }
    private Integer calculate
    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        int _lines_size = 0;
        _lines_size = Integer.parseInt(in.nextLine().trim());
        String[] _lines = new String[_lines_size];
        String _lines_item;
        for(int _lines_i = 0; _lines_i < _lines_size; _lines_i++) {
            try {
                _lines_item = in.nextLine();
            } catch (Exception e) {
                _lines_item = null;
            }
            _lines[_lines_i] = _lines_item;
        }

        res = validateArrayUsages(_lines);
        System.out.println(String.valueOf(res));

    }
}
