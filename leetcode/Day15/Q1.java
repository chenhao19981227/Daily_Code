package leetcode.Day15;

import java.util.LinkedList;

public class Q1 {
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if(c=='['){
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi=0;
                res=new StringBuilder();
            }else if(c==']'){
                StringBuilder temp=new StringBuilder();
                int mul=stack_multi.removeLast();
                for (int i = 0; i < mul; i++)temp.append(res);
                res=new StringBuilder(stack_res.pollLast()+temp);
            }else if(c>='0'&&c<='9'){
                multi=multi*10+(c-'0');
            }else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
