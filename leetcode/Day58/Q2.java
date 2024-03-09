package leetcode.Day58;

public class Q2 {
    public int reverseBits(int num) {
        int count=0, reverserCount=0;
        int result=0;
        for (int i = 0; i < 32; i++) {
            if((num&(1<<i))!=0){
                count++;
                reverserCount++;
            }else {
                reverserCount=count+1;
                count=0;
            }
            result=Math.max(reverserCount,result);
        }
        return result;
    }
}
