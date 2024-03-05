package leetcode.Day54;

import leetcode.TreeNode;

public class Q1 {
    public boolean isBalanced(TreeNode root) {
        if(root==null) return true;
        int left=dfs(root.left);
        int right=dfs(root.right);
        return Math.abs(left-right)<=1&&isBalanced(root.left)&&isBalanced(root.right);
    }
    private static int dfs(TreeNode root){
        if(root==null)return 0;
        return Math.max(dfs(root.left),dfs(root.right))+1;
    }
}
