package leetcode.Day68;

public class StreamRank {
    private TreeNode root = null;

    public StreamRank() {
    }
    public void track(int x) {
        root=insert(root,x);
    }
    public int getRankOfNumber(int x) {
        return getRank(root,x);
    }
    private TreeNode insert(TreeNode node, int x) {
        if(node==null){
            return new TreeNode(x);
        }
        if(node.val>=x){
            node.leftSize++;
            node.left=insert(node.left,x);
        }else {
            node.right=insert(node.right,x);
        }
        return node;
    }
    private int getRank(TreeNode node, int x) {
        if(node==null) return 0;
        if(x<node.val){
            return getRank(node.left,x);
        }else if(x>node.val){
            return getRank(node.right,x)+node.leftSize+1;
        }else {
            return node.leftSize+1;
        }
    }
    private class TreeNode {
        int val;
        int leftSize = 0;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
