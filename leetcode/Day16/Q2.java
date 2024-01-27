package leetcode.Day16;

import leetcode.ListNode;

public class Q2 {
    public ListNode deleteMiddle(ListNode head) {
        if(head.next==null)return null;
        ListNode fast=head;
        ListNode slow=new ListNode();
        slow.next=fast;
        while (fast.next!=null){
            fast=fast.next;
            slow=slow.next;
            if(fast.next!=null)fast=fast.next;
        }
        slow.next=slow.next.next;
        return head;
    }
}
