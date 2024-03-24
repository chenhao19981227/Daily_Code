package leetcode.Day73;

public class Q2 {
    static final int START=1900;
    public int maxAliveYear(int[] birth, int[] death) {
        int[] arr=new int[101];
        int len=birth.length;
        for (int i = 0; i < len; i++) {
            int bir=birth[i]-START,die=death[i]-START;
            arr[bir]++;
            if(die<100){
                arr[die+1]--;
            }
        }
        int max=0,sum=0,res=0;
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
            if(max<sum){
                max=sum;
                res=i;
            }
        }
        return res+START;
    }
}
