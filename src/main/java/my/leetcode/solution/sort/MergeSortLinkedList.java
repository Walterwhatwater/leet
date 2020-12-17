package my.leetcode.solution.sort;

import my.leetcode.solution.ListNode;

/**
 * https://leetcode-cn.com/problems/sort-list/
 */
public class MergeSortLinkedList {
    public ListNode<Integer> sortList(ListNode<Integer> head) {
        if (head == null || head.next == null)
            return head;

        // 1. 找到中点 slow
        ListNode<Integer> fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 2. 切分
        ListNode<Integer> tmp = slow.next;
        slow.next = null;

        // 3. 分割
        ListNode<Integer> left = sortList(head);
        ListNode<Integer> right = sortList(tmp);

        // 4. 合并
        ListNode<Integer> h = new ListNode<>(0);
        ListNode<Integer> res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }
}
