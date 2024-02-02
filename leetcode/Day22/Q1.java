package leetcode.Day22;

import leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Q1 {
    public int maxLevelSum(TreeNode root) {
        if(root==null)return 0;
        int max=Integer.MIN_VALUE;
        int res=0;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        int index=1;
        while (!queue.isEmpty()){
            int size=queue.size();
            int sum=0;
            for (int i = 0; i < size; i++) {
                TreeNode cur=queue.poll();
                sum+=cur.val;
                if(cur.left!=null)queue.offer(cur.left);
                if(cur.right!=null)queue.offer(cur.right);
            }
            if(sum>max){
                max=sum;
                res=index;
            }
            index++;
        }
        return res;
    }
}
