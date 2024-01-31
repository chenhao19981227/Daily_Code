package leetcode.Day20;

import leetcode.TreeNode;

public class Q1 {
    int res=0;
    public int pathSum(TreeNode root, int targetSum) {
        if(root==null)return 0;
        dfs(root,targetSum);
        pathSum(root.left,targetSum);
        pathSum(root.right,targetSum);
        return res;
    }
    public void dfs(TreeNode root,long targetSum){
        if(root==null)return;
        if(root.val==targetSum)res++;
        dfs(root.left,targetSum-root.val);
        dfs(root.right,targetSum-root.val);
    }
}
