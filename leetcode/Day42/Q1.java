package leetcode.Day42;

public class Q1 {
    public String replaceSpaces(String S, int length) {
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < length; i++) {
            if(S.charAt(i)==' '){
                sb.append("%20");
            }else {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }
}
