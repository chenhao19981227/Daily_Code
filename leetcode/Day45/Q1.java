package leetcode.Day45;

public class Q1 {
    public boolean isFlipedString(String s1, String s2) {
        return s1.length()==s2.length()&&(s1+s2).contains(s2);
    }
}
