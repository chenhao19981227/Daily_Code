package leetcode.Day47;

import leetcode.ListNode;

public class Q1 {
    public ListNode partition(ListNode head, int x) {
        ListNode head1=new ListNode(),head2=new ListNode();
        head1.next=head;
        ListNode index=head1,last=head2;
        while (index.next!=null){
            if(index.next.val<x){
                ListNode cur=index.next;
                index.next=cur.next;
                cur.next=null;
                last.next=cur;
                last=last.next;
            }else {
                index=index.next;
            }
        }
        last.next=head1.next;
        return head2.next;
    }

    public static void main(String[] args) {
        Q1 q1=new Q1();
        ListNode a=new ListNode(1);
        ListNode b=new ListNode(4);
        ListNode c=new ListNode(3);
        ListNode d=new ListNode(2);
        a.next=b;
        b.next=c;
        c.next=d;
        q1.partition(a,2);
    }
}
