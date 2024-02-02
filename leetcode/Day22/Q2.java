package leetcode.Day22;

import leetcode.TreeNode;

public class Q2 {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root==null||root.val==val)return root;
        if(root.val<val)return searchBST(root.right,val);
        return searchBST(root.left,val);
    }
}
