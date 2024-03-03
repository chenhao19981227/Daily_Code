package leetcode.Day52;

public class Q2 {
    private boolean[] visited = null;
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        this.visited = new boolean[graph.length];
        return helper(graph, start, target);
    }

    private boolean helper(int[][] graph, int start, int target) {
        for (int i = 0; i < graph.length; ++i) {
            if (!visited[i]) {
                if (graph[i][0] == start && graph[i][1] == target) {
                    return true;
                }
                visited[i] = true;
                if (graph[i][1] == target && helper(graph, start, graph[i][0])) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}
