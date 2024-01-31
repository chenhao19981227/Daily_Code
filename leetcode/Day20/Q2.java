package leetcode.Day20;

import leetcode.TreeNode;

public class Q2 {
    public int longestZigZag(TreeNode root) {
        return Math.max(dfs(root.left,1,true),dfs(root.right,1,false))-1;
    }
    public int dfs(TreeNode root,int preNum,boolean isLeft){
        if(root==null)return preNum;
        if(isLeft){
            return Math.max(dfs(root.left,1,true),dfs(root.right,preNum+1,false));
        }else{
            return Math.max(dfs(root.left,preNum+1,true),dfs(root.right,1,false));
        }
    }
}
