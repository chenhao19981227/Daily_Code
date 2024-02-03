package reconstruction.chapter8.split_loop;

public class SplitLoop {
    // 计算arr的总和，以及选取出最小值
    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3,4,5,6,7,8,9,10};
        int min=Integer.MAX_VALUE;
        int sum=0;
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
            min=Math.min(min,arr[i]);
        }
        System.out.println(sum);
        System.out.println(min);
    }
}
