package leetcode.Day57;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Q2 {
    private List<List<Integer>> ans;

    public List<List<Integer>> BSTSequences(TreeNode root) {
        ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if (root == null) {
            ans.add(path);
            return ans;
        }
        List<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        bfs(queue, path);
        return ans;
    }
    private void bfs(List<TreeNode> queue, List<Integer> path) {
        if (queue.isEmpty()) {
            ans.add(new ArrayList<>(path));
            return;
        }
        List<TreeNode> copy = new ArrayList<>(queue);
        for (int i = 0; i < queue.size(); i++) {
            TreeNode cur = queue.get(i);
            path.add(cur.val);
            queue.remove(i);
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
            bfs(queue, path);
            path.remove(path.size() - 1);
            queue = new ArrayList<>(copy);
        }
    }
}
