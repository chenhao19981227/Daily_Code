package leetcode.Day48;

import leetcode.ListNode;

public class Q2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1=0,len2=0;
        ListNode index=headA;
        while (index!=null){
            len1++;
            index=index.next;
        }
        index=headB;
        while (index!=null){
            len2++;
            index=index.next;
        }
        ListNode index1=null,index2=null;
        if(len1>len2){
            index1=headA;
            index2=headB;
        }else {
            index1=headB;
            index2=headA;
        }
        for (int i = 0; i < Math.abs(len1-len2); i++) {
            index1=index1.next;
        }
        while (index1!=null&&index2!=null){
            if(index1==index2)return index1;
            index1=index1.next;
            index2=index2.next;
        }
        return null;
    }
}
