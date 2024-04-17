package leetcode.Day97;

public class Q2 {
    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] res=new int[length];
        int intNum=w/32;
        for (int i = 0; i <= y; i++) {
            for (int j = 0; j < intNum; j++) {
                int index = j + intNum * i;
                if(i<y){
                    res[index]=0;
                }else {
                    int start = j * 32;
                    int end = (j + 1) * 32 - 1;
                    if(end<x1 ||start>x2){
                        res[index]=0;
                    }else if(start>=x1&&end<=x2){
                        res[index]=-1;
                    }else {
                        int sum=0;
                        for (int k = 0; k < 32; k++) {
                            if(start+k>=x1&&start+k<=x2){
                                sum+=(int)Math.pow(2,31-k);
                            }
                        }
                        res[index]=sum<0?sum+1:sum;
                    }
                }
                if(index==length-1)
                    return res;
            }
        }
        return res;
    }
}
