package leetcode.Day66;

public class Q1 {
    public void merge(int[] A, int m, int[] B, int n) {
        int index1=m-1,index2=n-1;
        int cur=m+n-1;
        while (index1>=0||index2>=0){
            int a=index1>=0?A[index1]:Integer.MIN_VALUE;
            int b=index2>=0?B[index2]:Integer.MIN_VALUE;
            if(a>=b){
                A[cur]=a;
                index1--;
            }else {
                A[cur]=b;
                index2--;
            }
            cur--;
        }
    }
}
