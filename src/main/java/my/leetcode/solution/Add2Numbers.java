package my.leetcode.solution;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Add2Numbers {
    public ListNode<Integer> addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        ListNode<Integer> p1 = l1;
        while (p1 != null) {
            stack1.push(p1.val);
            p1 = p1.next;
        }
        ListNode<Integer> p2 = l2;
        while (p2 != null) {
            stack2.push(p2.val);
            p2 = p2.next;
        }

        ListNode<Integer> dummy = new ListNode<>(0);
        ListNode<Integer> p = dummy;

        int bonus = 0;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int current = stack1.pop() + stack2.pop() + bonus;
            p.next = new ListNode<>(current % 10);
            bonus = current / 10;
            p = p.next;
        }

        while (!stack1.isEmpty()) {
            int current = stack1.pop() + bonus;
            p.next = new ListNode<>(current % 10);
            bonus = current / 10;
            p = p.next;
        }

        while (!stack2.isEmpty()) {
            int current = stack2.pop() + bonus;
            p.next = new ListNode<>(current % 10);
            bonus = current / 10;
            p = p.next;
        }

        if (bonus > 0) {
            p.next = new ListNode<>(bonus);
        }

        ListNode<Integer> head = dummy.next;
        dummy.next = null;
        return reverse(head);
    }

    private ListNode<Integer> reverse(ListNode<Integer> head) {
        ListNode<Integer> prev = null;
        ListNode<Integer> current = head;

        while (current != null) {
            ListNode<Integer> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    public int[] topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));

        // keep k top frequent elements in the heap
        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // build output list
        List<Integer> top_k = new LinkedList<>();
        while (!heap.isEmpty())
            top_k.add(heap.poll());
        Collections.reverse(top_k);
        return top_k.stream().mapToInt(t -> t).toArray();
    }

    public int jump(int[] nums) {
        int n = nums.length - 1;
        int steps = 0, position = 0;
        while (position < n) {
            if (position + nums[position] >= n) { //可以直接跳到终点
                steps++;
                break;
            }

            int max = 0;
            int maxi = 0;
            for (int i = 1; i <= nums[position]; i++) {//选择下次能跳到最远位置的下标
                if (max < i + nums[position + i]) {
                    max = i + nums[position + i];
                    maxi = i;
                }
            }
            position += maxi;
            steps++;
        }
        return steps;
    }
}
