package leetcode.Day147;

public class Q1 {
    public long minimumSteps(String s) {
        int count=0;
        long res=0L;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)=='0')
                count++;
            else
                res+=count;
        }
        return res;
    }
}
