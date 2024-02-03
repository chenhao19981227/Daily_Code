package reconstruction.chapter8.split_loop;

public class AfterSplitLoop {
    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3,4,5,6,7,8,9,10};
        int sum=calSum(arr);
        int min=calMin(arr);
        System.out.println(sum);
        System.out.println(min);
    }
    public static int calSum(int[] arr){
        int sum=0;
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
        }
        return sum;
    }
    public static int calMin(int[] arr){
        int min=Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            min=Math.min(min,arr[i]);
        }
        return min;
    }
}
