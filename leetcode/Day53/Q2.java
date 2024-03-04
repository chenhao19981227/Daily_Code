package leetcode.Day53;

import leetcode.ListNode;
import leetcode.TreeNode;

import java.util.*;

public class Q2 {
    public ListNode[] listOfDepth(TreeNode tree) {
        if(tree==null)return new ListNode[]{};
        Deque<TreeNode> queue=new ArrayDeque<>();
        queue.offer(tree);
        List<ListNode> list=new ArrayList<>();
        while (!queue.isEmpty()){
            int size=queue.size();
            ListNode head=new ListNode();
            ListNode index=head;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if(cur.left!=null) queue.offer(cur.left);
                if(cur.right!=null) queue.offer(cur.right);
                index.next=new ListNode(cur.val);
                index=index.next;
            }
            list.add(head.next);
        }
        return list.toArray(new ListNode[list.size()]);
    }
}
