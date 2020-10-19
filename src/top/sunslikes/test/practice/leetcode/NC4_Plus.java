package top.sunslikes.test.practice.leetcode;

import top.sunslikes.test.practice.nowcoder.ListNode;

/**
 * @ClassName: NC4_Plus
 * @Description: TODO
 * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。
 * 找到环的大小
 * 有环链表的定义：在链表中某个节点的next元素指向在它前面出现过的节点，则表明该链表存在环路。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: sunslikes
 * @Date: 2020/10/19 22:08
 * @Version: 1.0
 */
public class NC4_Plus {
    /**
     * 这个要算，https://blog.51cto.com/f1yinsky/2373303
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        boolean hit = false;
        // 看看快慢指针会不会碰撞
        // 如果是2倍速，那就一定会追上
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hit = true;
                break;
            }
        }
        if (!hit) {
            return null;
        }
        // 可以证明k = nR + d所以A与C一定会在入口相遇
        ListNode c = head; // 从头出发
        while (true) {
            // 避免循环链表
            if (c == slow) {
                break;
            }
            c = c.next;
            slow = slow.next;
        }
        ListNode in = slow;
        // 计算环长度
        int sum = 0;
        while (true) {
            slow = slow.next;
            c = c.next.next;
            sum++;
            if (slow == c) {
                break;
            }
        }
        return in;
    }
}
