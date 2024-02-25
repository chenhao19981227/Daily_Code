package leetcode.Day45;

import leetcode.ListNode;

import java.util.HashMap;
import java.util.Map;

public class Q2 {
    public ListNode removeDuplicateNodes(ListNode head) {
        if(head==null)return null;
        ListNode newHead=new ListNode();
        newHead.next=head;
        Map<Integer,Boolean> map=new HashMap<>();
        ListNode index=head,pre=newHead;
        while (index!=null){
            if(map.containsKey(index.val)){
                pre.next=index.next;
            }else {
                map.put(index.val,true);
                pre=index;
            }
            index=pre.next;
        }
        return newHead.next;
    }
}
