package leetcode.Day8;

public class Q3 {
    public boolean isSubsequence(String s, String t) {
        int index1=0,index2=0;
        if(s.length()==0)return true;
        while (index2<t.length()){
            char c1=s.charAt(index1);
            while (index2<t.length()&&c1!=t.charAt(index2))index2++;
            if(index2<t.length()){
                index1++;
                if(index1==s.length())return true;
                index2++;
            }
        }
        return false;
    }
}
