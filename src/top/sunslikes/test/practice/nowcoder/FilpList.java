package top.sunslikes.test.practice.nowcoder;


import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;

/**
 * 链表中的结点每k个一组翻转
 * 题目描述
 * 将给出的链表中的节点每\ k k 个一组翻转，返回翻转后的链表
 * 如果链表中的节点数不是\ k k 的倍数，将最后剩下的节点保持原样
 * 你不能更改节点中的值，只能更改节点本身。
 * 要求空间复杂度 O(1)
 * 例如：
 * 给定的链表是1\to2\to3\to4\to51→2→3→4→5
 * 对于k=2, 你应该返回 2\to 1\to 4\to 3\to 52→1→4→3→5
 * 对于k=3, 你应该返回 3\to2 \to1 \to 4\to 53→2→1→4→5
 */
public class FilpList {
    /**
     *
     * @param head ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public static ListNode reverseKGroup (ListNode head, int k) {
        // write code here
        ListNode now = head;
        ListNode begin = head;
        // 上组反转之后最后一个结点
        ListNode beforeLast = null;
        ListNode next = null;
        head = null;
        for (int i = 1; now != null; i++) {
            next = now.next;
            // 第k个就开始翻转
            if (i % k == 0) {
                if (head == null) {
                    head = now;
                }
                reverseLinkedList(begin, k, beforeLast);
                beforeLast = begin;
                begin = beforeLast.next;
            }
            now = next;
        }
        return head != null? head: begin;
    }
    // 将k个结点反转
    public static ListNode reverseLinkedList(ListNode begin, int k, ListNode beforLast) {
        ListNode now = begin, pre = null, next = null;
        for (int i = 0; i < k; ++i) {
            next = now.next;
            now.next = pre;
            pre = now;
            now = next;
        }
        begin.next = now;
        if (beforLast != null) {
            beforLast.next = pre;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode head = null;
        ListNode now = null;
        int[] a = {1,2,3,4,5};
        for (int i: a) {
            ListNode tmp = new ListNode();
            tmp.val = i;
            tmp.next = null;
            if (head == null) {
                head = now = tmp;
            } else {
                now.next = tmp;
                now = tmp;
            }
        }
        head = reverseKGroup(head, 6);
        while (head != null) {
            System.out.println(head);
            head = head.next;
        }
    }
}
