package leetcode.Day57;

import leetcode.TreeNode;

public class Q1 {
    public int pathSum(TreeNode root, int sum) {
        if(root==null)return 0;
        return dfs(root,sum)+pathSum(root.left,sum)+pathSum(root.right,sum);
    }
    public static int dfs(TreeNode root,int target){
        int count=0;
        if(root==null)return 0;
        int val=root.val;
        if(val==target) count++;
        count+=dfs(root.left,target-val);
        count+=dfs(root.right,target-val);
        return count;
    }
}
