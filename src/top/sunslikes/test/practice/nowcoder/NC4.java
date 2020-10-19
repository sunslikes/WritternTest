package top.sunslikes.test.practice.nowcoder;

import java.util.HashSet;
import java.util.List;

/**
 * @ClassName: NC4
 * @Description: TODO
 * 题目描述
 * 判断给定的链表中是否有环
 * @Author: sunslikes
 * @Date: 2020/10/19 17:27
 * @Version: 1.0
 */
public class NC4 {
    /**
     * 使用快慢指针 快指针步长为2
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        boolean hit = false;
        // 看看快慢指针会不会碰撞
        // 如果是2倍速，那就一定会追上
        while (!hit && fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hit = true;
            }
        }
        return hit;
    }
    // 空间复杂度较高，直接使用set判断
    public boolean hasCycle1(ListNode head) {
        HashSet<ListNode> nodeHashSet = new HashSet<>();
        ListNode now = head;
        while (now != null) {
            if (nodeHashSet.contains(now)) {
                return true;
            }
            nodeHashSet.add(now);
            now = now.next;
        }
        return false;
    }
}
