package leetcode.Day55;

import leetcode.TreeNode;

public class Q1 {
    public boolean isValidBST(TreeNode root) {
        return dfs(root,null,null);
    }
    private static boolean dfs(TreeNode root, Integer min,Integer max){
        if(root==null)return true;
        int val = root.val;
        if(min!=null&& val <=min) return false;
        if(max!=null&& val >=max) return false;
        if(!dfs(root.left,min, val)) return false;
        if(!dfs(root.right, val,max)) return false;
        return true;
    }
}
