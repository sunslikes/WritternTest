package top.sunslikes.test.practice.nowcoder;

/**
 * 题目描述
 * 给定一个链表，删除链表的倒数第n个节点并返回链表的头指针
 * 例如，
 *  给出的链表为:1->2->3->4->5, n= 2.
 *  删除了链表的倒数第n个节点之后,链表变为1->2->3->5.
 * 备注：
 * 题目保证n一定是有效的
 * 请给出请给出时间复杂度为\ O(n) O(n)的算法
 */
public class NC53 {
    /**
     * 快慢指针。但是不是步长不同，而是出发时间不同
     * @param head ListNode类
     * @param n int整型
     * @return ListNode类
     */
    public ListNode removeNthFromEnd (ListNode head, int n) {
        // write code here
        ListNode slow, fast, pre;
        slow = fast = pre = head;
        while (n > 0) {
            n--;
            fast = fast.next;
        }
        while(fast != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        if (slow == pre) {
            return head == null? null: head.next;
        }
        pre.next = slow.next;
        return head;
    }
}
