package leetcode.Day151;

import java.util.ArrayList;
import java.util.List;

public class Q2 {
    static int count=0;
    public static void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(),A,B,C);
    }
    private static void move(int size,List<Integer> start,List<Integer> middle,List<Integer> target){
        if(size==1){
            target.add(start.remove(start.size()-1));
            return;
        }
        move(size-1,start,target,middle);
        target.add(start.remove(start.size()-1));
        move(size-1,middle,start,target);
    }

    public static void main(String[] args) {
        List<Integer> a=new ArrayList<>();
        List<Integer> b=new ArrayList<>();
        List<Integer> c=new ArrayList<>();
        a.add(8);
        a.add(6);
        a.add(2);
        b.add(7);
        b.add(5);
        b.add(4);
        b.add(3);
        b.add(1);
        hanota(a,b,c);
        System.out.println(count);
    }
}
