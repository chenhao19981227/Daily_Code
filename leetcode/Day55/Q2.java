package leetcode.Day55;

import leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q2 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Deque<TreeNode> stack=new ArrayDeque<>();
        TreeNode index=root,pre=null;
        while (!stack.isEmpty()||index!=null){
            while (index!=null){
                stack.push(index);
                index=index.left;
            }
            index=stack.pop();
            if(pre==p) return index;
            pre=index;
            index=index.right;
        }
        return null;
    }
}
