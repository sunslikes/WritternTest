package top.sunslikes.test.practice.nowcoder;

/**
 * 题目描述
 * 将两个有序的链表合并为一个新链表，要求新的链表是通过拼接两个链表的节点来生成的。
 */
public class NC33 {
    /**
     *
     * @param l1 ListNode类
     * @param l2 ListNode类
     * @return ListNode类
     */
    public ListNode mergeTwoLists (ListNode l1, ListNode l2) {
        // write code here
        ListNode head = null;
        ListNode now = null, pre = null;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                now = l1;
                l1 = l1.next;
            } else {
                now = l2;
                l2 = l2.next;
            }
            if (head == null) {
                head = now;
                pre = now;
                continue;
            }
            pre.next = now;
            pre = now;
        }
        // 说明起码有一个链表是null
        if (now == null) {
            head = l1 != null? l1: l2;
            return head;
        }
        // 连接
        now.next = l1 != null? l1: l2;
        return head;
    }
}
