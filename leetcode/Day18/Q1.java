package leetcode.Day18;

import leetcode.ListNode;

public class Q1 {
    public ListNode reverseList(ListNode head) {
        if(head==null)return null;
        ListNode newHead=new ListNode();
        newHead.next=head;
        ListNode cur=head.next;
        while (cur!=null){
            head.next=cur.next;
            cur.next=newHead.next;
            newHead.next=cur;
            cur=head.next;
        }
        return newHead.next;
    }
}
