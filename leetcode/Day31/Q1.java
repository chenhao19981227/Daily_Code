package leetcode.Day31;

public class Q1 {
    public int minEatingSpeed(int[] piles, int h) {
        int low=1,hight=0;
        for(int pile:piles)hight=Math.max(hight,pile);
        int res=hight;
        while(low<hight){
            int speed=(hight-low)/2+low;
            long time=calculateTime(piles,speed);
            if(time<=h){
                hight=speed;
                res=speed;
            }else{
                low=speed+1;
            }
        }
        return res;
    }
    public long calculateTime(int[] piles,int speed){
        long time=0;
        for(int pile:piles){
            time+=(pile+speed-1)/speed;
        }
        return time;
    }
}
