package leetcode.Day12;

import java.util.*;

public class Q1 {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        return Arrays.asList(find(nums1,nums2),find(nums2,nums1));
    }
    private List<Integer> find(int[] nums1, int[] nums2) {
        Set<Integer> set=new HashSet<>();
        for (int num : nums2) {
            set.add(num);
        }
        Set<Integer> res=new HashSet<>();
        for (int num : nums1) {
            if(!set.contains(num)){
                res.add(num);
            }
        }
        return new ArrayList<>(res);
    }
}
