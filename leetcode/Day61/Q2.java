package leetcode.Day61;

import java.util.List;

public class Q2 {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
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
}
