package leetcode.Day56;

import leetcode.TreeNode;

public class Q2 {
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if(t1==null)return false;
        if(t2==null)return true;
        return isEqual(t1,t2)||checkSubTree(t1.left,t2)||checkSubTree(t1.right,t2);
    }

    private boolean isEqual(TreeNode t1, TreeNode t2) {
        if(t1==t2) return true;
        if(t1==null||t2==null) return false;
        return t1.val==t2.val&&isEqual(t1.left,t2.left)&&isEqual(t1.right,t2.right);
    }
}
