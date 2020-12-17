package my.leetcode.solution;

import java.util.LinkedList;
import java.util.Queue;

public class NumDecoding {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return root;
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return backtrack("", s, 0);
    }

    private int backtrack(String part, String s, int i) {
        if (i == s.length() - 1) {
            if (s.length() == 1 && part.equals("0")) {
                return 0;
            }

            int code = Integer.parseInt(part + s.charAt(i));
            if (code >= 1 && code <= 26) {
                return 1;
            } else {
                return 0;
            }
        }

        int result = 0;

        if (part.length() == 0) {
            String temp = String.valueOf(s.charAt(i));
            int code = Integer.parseInt(temp);
            if (code >= 1 && code <= 26) {
                result += backtrack("", s, i + 1);
            }
            result += backtrack(temp, s, i + 1);
        } else if (part.length() == 1 && !part.equals("0")) {
            String temp = part + s.charAt(i);
            int code = Integer.parseInt(temp);
            if (code >= 1 && code <= 26) {
                result += backtrack("", s, i + 1);
            }
        }

        return result;
    }
}
