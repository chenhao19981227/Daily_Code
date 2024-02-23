package leetcode.Day43;

public class Q1 {
    public boolean oneEditAway(String first, String second) {
        int len1=first.length(),len2=second.length();
        if(Math.abs(len1-len2)>1)return false;
        int pre1=0,pre2=0;
        int q1=len1-1,q2=len2-1;
        while (pre1<=q1&&pre2<=q2){
            if(first.charAt(pre1)==second.charAt(pre2)){
                pre1++;
                pre2++;
                continue;
            }
            if(first.charAt(q1)==second.charAt(q2)){
                q1--;
                q2--;
                continue;
            }else {
                break;
            }
        }
        return q1 - pre1 + q2 - pre2 <= 0 && Math.abs(q1 - q2) <= 1;
    }
}
