package leetcode.Day48;

import leetcode.ListNode;

public class Q1 {
    public boolean isPalindrome(ListNode head) {
        if(head==null)return true;
        ListNode fast=head,slow=head;
        while (fast.next!=null){
            fast=fast.next;
            if(fast.next!=null){
                fast=fast.next;
                slow=slow.next;
            }
        }
        fast=slow.next;
        slow.next=null;
        ListNode reFast=reverse(fast);
        while (head!=null&&reFast!=null){
            if(head.val!=reFast.val)return false;
            head=head.next;
            reFast=reFast.next;
        }
        return true;
    }
    private ListNode reverse(ListNode fast) {
        ListNode head=new ListNode();
        ListNode cur=fast;
        while (cur!=null){
            fast=cur.next;
            cur.next=head.next;
            head.next=cur;
            cur=fast;
        }
        return head.next;
    }
}
