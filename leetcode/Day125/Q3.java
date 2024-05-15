package leetcode.Day125;

import java.util.*;

public class Q3 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int target_sum=sc.nextInt();
        int num=sc.nextInt();
        int[] arr=new int[num];
        int arr_sum=0;
        for (int i = 0; i < num; i++) {
            arr[i]=sc.nextInt();
            arr_sum+=arr[i];
        }
        if(arr_sum!=2*target_sum){
            System.out.println(-1);
        }else {
            Arrays.sort(arr);
            List<Integer> part1 = split(arr, target_sum);
            if(part1.isEmpty()){
                System.out.println(-1);
            }else {
                List<Integer> part2=remain(arr,part1);
                int part2_sum=0;
                for (Integer i : part2) {
                    part2_sum+=i;
                }
                if(part2_sum==target_sum){
                    int size1= part1.size();
                    for (int i = 0; i < size1; i++) {
                        if(i==size1-1){
                            System.out.print(part1.get(i));
                        }else {
                            System.out.print(part1.get(i)+" ");
                        }
                    }
                    System.out.println();
                    int size2=part2.size();
                    for (int i = 0; i < size2; i++) {
                        if(i==size2-1){
                            System.out.print(part2.get(i));
                        }else {
                            System.out.print(part2.get(i)+" ");
                        }
                    }
                }else {
                    System.out.println(-1);
                }
            }
        }
    }
    public static List<Integer> split(int[] nums, int target) {
        List<Integer> result = new ArrayList<>();
        dfs(nums, target, 0, result);
        return result;
    }

    private static boolean dfs(int[] nums, int target, int start, List<Integer> combination) {
        if (target == 0) {
            return true;
        }
        if (target < 0) {
            return false;
        }

        for (int i = start; i < nums.length; i++) {
            combination.add(nums[i]);
            if (dfs(nums, target - nums[i], i + 1, combination)) {
                return true;
            }
            combination.remove(combination.size() - 1);
        }
        return false;
    }
    public static List<Integer> remain(int[] nums, List<Integer> combination) {
        Set<Integer> combinationSet = new HashSet<>(combination);
        List<Integer> remaining = new ArrayList<>();

        for (int num : nums) {
            if (!combinationSet.contains(num)) {
                remaining.add(num);
            }
        }

        return remaining;
    }
}
