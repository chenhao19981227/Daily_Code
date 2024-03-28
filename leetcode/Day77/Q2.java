package leetcode.Day77;

import java.util.HashSet;
import java.util.Set;

public class Q2 {
    public int[] findSwapValues(int[] array1, int[] array2) {
        int sum1 = 0, sum2 = 0;
        Set<Integer> container = new HashSet<>();
        for (int num : array1) sum1 += num;
        for (int num : array2) {
            container.add(num);
            sum2 += num;
        }
        int diff = sum1 - sum2;
        if (diff % 2 != 0) return new int[]{};
        diff /= 2;
        for (int num : array1) if (container.contains(num - diff)) return new int[]{num, num - diff};
        return new int[]{};
    }
}
