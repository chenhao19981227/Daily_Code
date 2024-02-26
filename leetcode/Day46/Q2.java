package leetcode.Day46;

import leetcode.ListNode;

public class Q2 {
    public void deleteNode(ListNode node) {
        ListNode pre=null;
        while (node.next!=null){
            node.val=node.next.val;
            pre=node;
            node=node.next;
        }
        pre.next=null;
    }
}
