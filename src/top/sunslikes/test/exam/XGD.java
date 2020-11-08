package top.sunslikes.test.exam;
import java.util.*;

public class XGD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        char[] chars = s.toCharArray();
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0, j = chars.length - 1; i <= j; ) {
            // 左右比较找最小的, 如果相等就继续往里面找
            if (chars[i] == chars[j]) {
                if (pick(chars, i+1, j-1) == 0) {
                    index = i++;
                } else {
                    index = j--;
                }
            } else if (chars[i] < chars[j]) {
                index = i++;
            } else {
                index = j--;
            }
            sb.append(chars[index]);

        }
        System.out.println(sb);
    }
    public static int pick(char[] a, int i, int j) {
        if (i >= j) {
            return 0;
        }
        if (a[i] == a[j]) {
            return pick(a, i + 1, j - 1);
        }
        // 0表示左边i
        if (a[i] < a[j]) {
            return 0;
        }
        return 1;
    }
}