package leetcode.Day85;

import leetcode.TreeNode;

public class Q2 {
    TreeNode head=new TreeNode(0);
    TreeNode pre=head;
    public TreeNode convertBiNode(TreeNode root) {
        dfs(root);
        return head.right;
    }
    private void dfs(TreeNode root){
        if(root==null)return;
        dfs(root.left);
        pre.right=root;
        pre=root;
        root.left=null;
        dfs(root.right);
    }
}
