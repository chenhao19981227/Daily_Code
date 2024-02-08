package leetcode.Day28;

public class Q1 {
    public int guessNumber(int n) {
        int left=1,right=n;
        while (left<=right){
            int mid=(right-left)/2+left;
            int result=guess(mid);
            if(result==0)return mid;
            if(result==-1)right=mid-1;
            if(result==1)left=mid+1;
        }
        return -1;
    }
    private int guess(int mid) {
        return 0;
    }
}
