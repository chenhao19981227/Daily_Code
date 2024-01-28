package leetcode.Day17;

import leetcode.ListNode;

public class Q2 {
    public int pairSum(ListNode head) {
        ListNode slow=head,fast=head;
        while (fast!=null){
            fast=fast.next;
            slow=slow.next;
            if(fast!=null)fast=fast.next;
        }
        assert slow != null;
        ListNode head2=reverse(slow);
        int max=0;
        while (head2!=null){
            max=Math.max(max,head.val+head2.val);
            head=head.next;
            head2=head2.next;
        }
        return max;
    }
    public ListNode reverse(ListNode head){
        ListNode newHead=new ListNode();
        newHead.next=head;
        ListNode index=head.next;
        while (index!=null){
            head.next=index.next;
            index.next=newHead.next;
            newHead.next=index;
            index=head.next;
        }
        return newHead.next;
    }
}
