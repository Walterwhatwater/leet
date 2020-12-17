package my.leetcode.solution.tree;

import java.util.Stack;

public class VerifyPreorderSequenceInBinarySearchTree {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return false;
        }

        if (preorder.length == 1) {
            return true;
        }

        // 单调递减栈
        Stack<Integer> stack = new Stack<>();
        int min = Integer.MIN_VALUE;

        for (int n : preorder) {
            if (n < min) {
                return false;
            }

            if (stack.isEmpty() || n < stack.peek()) {
                stack.push(n);
            } else {
                int bottom = Integer.MIN_VALUE;
                while (!stack.isEmpty() && n > stack.peek()){
                    bottom = Math.max(bottom, stack.pop());
                }
                min = bottom;

                stack.push(n);
            }
        }

        return true;
    }
}
