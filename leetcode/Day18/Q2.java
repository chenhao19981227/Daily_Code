package leetcode.Day18;

import leetcode.TreeNode;

public class Q2 {
    public int maxDepth(TreeNode root) {
        if(root==null)return 0;
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }
}
