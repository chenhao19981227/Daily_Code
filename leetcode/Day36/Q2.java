package leetcode.Day36;

public class Q2 {
    public int minFlips(int a, int b, int c) {
        String str1 = Integer.toBinaryString(a);
        String str2 = Integer.toBinaryString(b);
        String str3 = Integer.toBinaryString(c);
        int result=0;
        int len1=str1.length(),len2=str2.length(),len3=str3.length();
        for(int i=len3-1;i>=0;i--){
            char c1=(i-(len3-len1)>=0)?str1.charAt(i-(len3-len1)):'0';
            char c2=(i-(len3-len2)>=0)?str2.charAt(i-(len3-len2)):'0';
            if(str3.charAt(i)=='1'){
                if(c1=='0'&&c2=='0')result++;
            }else {
                if(c1=='1')result++;
                if(c2=='1')result++;
            }
        }
        if(len1>len3){
            for (int i = 0; i < len1 - len3; i++) {
                if(str1.charAt(i)=='1')result++;
            }
        }
        if(len2>len3){
            for (int i = 0; i < len2 - len3; i++) {
                if(str2.charAt(i)=='1')result++;
            }
        }
        return result;
    }
}
