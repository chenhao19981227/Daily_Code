package leetcode.Day53;

import leetcode.TreeNode;

public class Q1 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length==0)return null;
        return dfs(nums,0,nums.length-1);
    }
    private static TreeNode dfs(int[] nums,int left,int right){
        if(left>right) return null;
        int mid=(right-left)/2+left;
        TreeNode cur=new TreeNode(nums[mid]);
        cur.left=dfs(nums,left,mid-1);
        cur.right=dfs(nums,mid+1,right);
        return cur;
    }
}
