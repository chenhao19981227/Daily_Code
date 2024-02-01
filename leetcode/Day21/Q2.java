package leetcode.Day21;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q2 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        if(root==null)return res;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if(cur.left!=null)queue.offer(cur.left);
                if(cur.right!=null)queue.offer(cur.right);
                if(i==size-1)res.add(cur.val);
            }
        }
        return res;
    }
}
