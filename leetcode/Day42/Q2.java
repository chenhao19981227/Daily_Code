package leetcode.Day42;

public class Q2 {
    public boolean canPermutePalindrome(String s) {
        int count=0;
        int[] mark=new int[128];
        for (int i = 0; i < s.length(); i++) {
            int index=s.charAt(i);
            mark[index]++;
            if(mark[index]%2==1)
                count++;
            else
                count--;
        }
        return count==0||count==1;
    }
}
