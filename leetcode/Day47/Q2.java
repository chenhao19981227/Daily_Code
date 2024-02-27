package leetcode.Day47;

import leetcode.ListNode;

public class Q2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(),index=head;
        int up=0;
        while (l1!=null||l2!=null){
            int num1=l1==null?0: l1.val;
            int num2=l2==null?0:l2.val;
            int sum=num1+num2+up;
            index.next=new ListNode(sum%10);
            index=index.next;
            up=sum/10;
            if(l1!=null)l1=l1.next;
            if(l2!=null)l2=l2.next;
        }
        if(up==1)index.next=new ListNode(up);
        return head.next;
    }
}
