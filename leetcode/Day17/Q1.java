package leetcode.Day17;

import leetcode.ListNode;

public class Q1 {
    public ListNode oddEvenList(ListNode head) {
        if(head==null||head.next==null)return head;
        ListNode odd=head,even=odd.next;
        ListNode evenHead=even;
        while (true){
            if(odd.next!=null&&even.next!=null){
                odd.next=even.next;
                odd=odd.next;
                even.next=odd.next;
                even=even.next;
            }else {
                break;
            }
        }
        odd.next=evenHead;
        return head;
    }
}
