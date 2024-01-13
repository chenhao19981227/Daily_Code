package leetcode.Day2;

public class Q1 {
    // 第7题 整数反转 数学
    public int reverse(int x) {
        int res=0;
        while (x!=0){
            if(res<Integer.MIN_VALUE/10||res>Integer.MAX_VALUE/10){
                return 0;
            }
            int dig=x%10;
            x/=10;
            res=res*10+dig;
        }
        return res;
    }
}
