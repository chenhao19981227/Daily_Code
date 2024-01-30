package leetcode.Day19;

import leetcode.TreeNode;

public class Q2 {
    int res;
    public int goodNodes(TreeNode root) {
        res=0;
        dfs(root,root.val);
        return res;
    }
    public void dfs(TreeNode root,int max){
        if(root==null)return;
        if(root.val>=max){
            res++;
            max=root.val;
        }
        dfs(root.left,max);
        dfs(root.right,max);
    }
}
