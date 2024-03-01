package leetcode.Day50;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class StackOfPlates {
    final List<Stack<Integer>> stacks = new ArrayList<>();
    final int cap;

    public StackOfPlates(int cap) {
        this.cap = cap;
    }

    public void push(int val) {
        if (cap == 0) return;
        if (stacks.isEmpty()) stacks.add(new Stack<>());
        if (stacks.get(stacks.size() - 1).size() == cap) stacks.add(new Stack<>());
        stacks.get(stacks.size() - 1).push(val);
    }

    public int pop() {
        return popAt(stacks.size() - 1);
    }

    public int popAt(int index) {
        if (cap == 0 || index < 0 || index >= stacks.size()) return -1;
        int pop = stacks.get(index).pop();
        if (stacks.get(index).empty()) stacks.remove(index);
        return pop;
    }
}
