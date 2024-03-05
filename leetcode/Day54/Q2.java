package leetcode.Day54;

import leetcode.TreeNode;

public class Q2 {
    public boolean isBalanced(TreeNode root) {
        return dfs(root)>1;
    }
    private static int dfs(TreeNode root){
        if(root==null)return 0;
        int left=dfs(root.left);
        int right=dfs(root.right);
        if(left==-1||right==-1||Math.abs(left-right)>1) return -1;
        return Math.max(left,right)+1;
    }
}
