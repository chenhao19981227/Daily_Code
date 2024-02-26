package leetcode.Day46;

import leetcode.ListNode;

public class Q1 {
    public int kthToLast(ListNode head, int k) {
            ListNode q=head,p=head;
        for (int i = 0; i < k - 1; i++) {
            q=q.next;
        }
        while (q.next!=null){
            q=q.next;
            p=p.next;
        }
        return p.val;
    }
}
