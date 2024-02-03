package leetcode.Day23;

import leetcode.TreeNode;

public class Q1 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null)return null;
        if(root.val>key){
            root.left=deleteNode(root.left,key);
            return root;
        }
        if(root.val<key){
            root.right=deleteNode(root.right,key);
            return root;
        }
        if (root.left == null && root.right == null) return null;
        if(root.left==null)return root.right;
        if(root.right==null) return root.left;
        TreeNode son=root.left;
        while (son.right!=null)son=son.right;
        root.left=deleteNode(root.left,son.val);
        son.left=root.left;
        son.right=root.right;
        return son;
    }
}
