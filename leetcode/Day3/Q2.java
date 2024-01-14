package leetcode.Day3;

public class Q2 {
    // 151 反转字符串中的单词 字符串操作
    public String reverseWords(String s) {
        StringBuilder res=new StringBuilder();
        String trim = s.trim();
        String[] splits = trim.split(" ");
        for(int i=splits.length-1;i>=0;i--){
            String string=splits[i];
            if(string.equals(""))continue;
            res.append(string);
            if(i!=0)res.append(" ");
        }
        return res.toString();
    }
}
