package top.sunslikes.test.practice.nowcoder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: NC93
 * @Description: 设置LRU缓存结构 https://leetcode-cn.com/problems/lru-cache/solution/lruhuan-cun-ji-zhi-by-leetcode-solution/
 * 题目描述
 * 设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能
 * set(key, value)：将记录(key, value)插入该结构
 * get(key)：返回key对应的value值
 * [要求]
 * set和get方法的时间复杂度为O(1)
 * 某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的。
 * 当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
 * 若opt=1，接下来两个整数x, y，表示set(x, y)
 * 若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
 * 对于每个操作2，输出一个答案
 * 示例1
 * 输入
 * 复制
 * [[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
 * 输出
 * 复制
 * [1,-1]
 * 说明
 * 第一次操作后：最常使用的记录为("1", 1)
 * 第二次操作后：最常使用的记录为("2", 2)，("1", 1)变为最不常用的
 * 第三次操作后：最常使用的记录为("3", 2)，("1", 1)还是最不常用的
 * 第四次操作后：最常用的记录为("1", 1)，("2", 2)变为最不常用的
 * 第五次操作后：大小超过了3，所以移除此时最不常使用的记录("2", 2)，加入记录("4", 4)，并且为最常使用的记录，然后("3", 2)变为最不常使用的记录
 * 备注:
 * 1 \leq K \leq N \leq 10^51≤K≤N≤10
 * 5
 *
 * -2 \times 10^9 \leq x,y \leq 2 \times 10^9−2×10
 * 9
 *  ≤x,y≤2×10
 * 9
 * @Author: sunslikes
 * @Date: 2020/10/19 9:27
 * @Version: 1.0
 */
public class NC93 {
    // 最无脑，用jdk提供的LinkedHashMap实现
    class LRUCache extends LinkedHashMap<Integer, Integer> {
        private int capacity;
        public LRUCache(int capacity) {
            // accessOrder基于访问顺序
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }
        public int get(int key) {
            return super.getOrDefault(key, -1);
        }
        public void set(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    // 利用双向链表加速插入和删除，从前到后表示新到旧节点
    class LRUCache1 {
        // 双向链表
        public LinkedList<Integer> linkedList = new LinkedList();
        // 哈希表
        public HashMap<Integer, Integer> map = new HashMap<>();
        private int capacity;
        private LRUCache1(){}
        LRUCache1(int capacity) {
            this.capacity = capacity;
        }
        public int get(int key) {
            Integer result = map.get(key);
            // 访问过的节点提到最前面
            if (result != null) {
                // 注意remove如果传入基本类型int就直接删除第index而不是删除具体的节点
                linkedList.remove(new Integer(key));
                linkedList.addFirst(key);
                return result;
            } else {
                return -1;
            }
        }
        public void set(int key, int value) {
            Integer old = map.get(key);
            // 为空说明是新加入的数据
            if (old == null) {
                // 如果新加入之后的大小会超过容量，那就删除最后一个节点
                if (linkedList.size() >= capacity) {
                    Integer removeKey = linkedList.removeLast();
                    map.remove(removeKey);
                }
                linkedList.addFirst(key);
                map.put(key, value);
            } else {
                // 更新map值
                map.put(key, value);
                // 将节点提到开始节点
                linkedList.remove(new Integer(key));
                linkedList.addFirst(key);
            }
        }
    }

    public int[] LRU (int[][] operators, int k) {
        // write code here
        LRUCache1 lruCache = new LRUCache1(k);
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < operators.length; ++i) {
            if (operators[i][0] == 1) {
                lruCache.set(operators[i][1], operators[i][2]);
            } else {
                integers.add(lruCache.get(operators[i][1]));
            }
        }
        System.out.println(integers);
        return integers.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[][] a = {
                {1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}
        };
        NC93 nc93 = new NC93();
        nc93.LRU(a, 3);
    }
}
