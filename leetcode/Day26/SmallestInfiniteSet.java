package leetcode.Day26;

import java.util.TreeSet;

class SmallestInfiniteSet {
    TreeSet<Integer> set;
    int thresh;

    public SmallestInfiniteSet() {
        thresh=1;
        set = new TreeSet<Integer>();
    }

    public int popSmallest() {
        if(set.isEmpty())return thresh++;
        return set.pollFirst();
    }

    public void addBack(int num) {
        if(num<thresh)set.add(num);
    }
}
