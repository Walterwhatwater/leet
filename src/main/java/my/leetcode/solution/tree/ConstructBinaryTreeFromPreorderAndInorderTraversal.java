package my.leetcode.solution.tree;

import my.leetcode.solution.TreeNode;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode build(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        if (pEnd == pStart) {
            return null;
        }

        int rootValue = preorder[pStart];
        TreeNode root = new TreeNode(rootValue);

        int rootIndex = 0;
        for (int i = iStart; i < iEnd; ++i) {
            if (inorder[i] == rootValue) {
                rootIndex = i;
                break;
            }
        }

        int leftCount = rootIndex - iStart;

        root.left = build(preorder, pStart + 1, pStart + leftCount + 1, inorder, iStart, rootIndex);
        root.right = build(preorder, pStart + leftCount + 1, pEnd, inorder, rootIndex + 1, iEnd);
        return root;
    }
}
