package leetcode.Day81;

public class Q1 {
    public int convertInteger(int A, int B) {
        int res=0;
        for(int i=0;i<32;i++){
            res+=(A&1)^(B&1);
            A=A>>1;
            B=B>>1;
        }
        return res;
    }
}
