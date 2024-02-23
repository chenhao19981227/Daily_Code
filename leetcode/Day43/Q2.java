package leetcode.Day43;

public class Q2 {
    public String compressString(String S) {
        int len=S.length();
        if(len==0)return S;
        StringBuilder sb=new StringBuilder();
        char pre=S.charAt(0);
        int index=0;
        int count=0;
        while (index<=len){
            if(index==len||S.charAt(index)!=pre){
                sb.append(pre);
                sb.append(count);
                if(index<len){
                    pre=S.charAt(index);
                    count=0;
                }else{
                    break;
                }
            }else {
                index++;
                count++;
            }
        }
        return sb.toString().length()<len?sb.toString():S;
    }
}
