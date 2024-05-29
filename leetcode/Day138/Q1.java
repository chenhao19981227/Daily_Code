package leetcode.Day138;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        List<List<Integer>> result = new ArrayList<>();
        backTrack(result, new ArrayList<>(), m, n, 1, 7);
        int[] count=new int[7];
        for (List<Integer> list :result) {
            for (Integer i : list) {
                count[i-1]++;
            }
        }
        for (int i : count) {
            System.out.println(i);
        }
    }
    private static void backTrack(List<List<Integer>> result, List<Integer> tempList, int remainingSum, int n, int start, int maxVal) {
        if (tempList.size() == n) {
            if (remainingSum == 0) {
                result.add(new ArrayList<>(tempList));
            }
            return;
        }
        for (int i = start; i <= maxVal; i++) {
            if (remainingSum - i < 0) {
                break;
            }
            if (remainingSum < i * (n - tempList.size())) {
                break;
            }
            if (remainingSum > i + maxVal * (n - tempList.size() - 1)) {
                continue;
            }
            tempList.add(i);
            backTrack(result, tempList, remainingSum - i, n, i, maxVal);
            tempList.remove(tempList.size() - 1);
        }
    }
}

