package my.leetcode.solution;

public class ListNode<T extends Comparable<T>> {
    public T val;
    public ListNode<T> next;

    public ListNode(T x) {
        val = x;
    }

    public int compare(ListNode<T> another) {
        return this.val.compareTo(another.val);
    }
}
