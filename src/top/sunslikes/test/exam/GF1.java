package top.sunslikes.test.exam;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 广发
 */
public class GF1 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 返回前n个出现次数频率最低的字符，次数相同的字符则按ASCII值降序输出
     * @param data string字符串
     * @param n int整型
     * @return string字符串
     */
    public String lastNChar (String data, int n) {
        // write code here
        char[] chars = data.toCharArray();
        List<Entry> entries = new ArrayList<>();
        for (char c : chars) {
            int index = getIndex(entries, c);
            // 没出现过
            if (index == -1) {
                entries.add(new Entry(c));
            } else {
                Entry entry = entries.get(index);
                entry.count++;
                entries.set(index, entry);
            }
        }
        entries.sort(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o1.count < o2.count? -1: 0;
            }
        });
        entries = entries.subList(0,n > entries.size()? entries.size(): n);
        List<Character> collect = entries.stream().map((x) -> {
            return x.value;
        }).collect(Collectors.toList());
        return collect.toString();
    }

    public static void main(String[] args) {
        ArrayList<A> as = new ArrayList<>();
        as.add(new A(1, 3));
        as.add(new A(0, 1));
        as.add(new A(4, 4));
        System.out.println(as);
        as.sort(((o1, o2) -> o1.count > o2.count? 1: (o1.count < o2.count? -1: 0)));
    }

    static class  A {
        int val;
        int count;
        A(int val, int count) {
            this.val = val;
            this.count = count;
        }

        @Override
        public String toString() {
            return "A{" +
                    "val=" + val +
                    ", count=" + count +
                    '}';
        }
    }

    private int getIndex(List<Entry> a, char c) {
        int index = -1;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).value == c) {
                index = i;
                break;
            }
        }
        return index;
    }
    class Entry {
        char value;
        int count;
        Entry(char c) {
            this.value = c;
            this.count = 1;
        }

    }
}
