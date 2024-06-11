package leetcode.Day151;

import java.util.*;

public class Q3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取n和m
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine(); // 读取换行符

        // 读取IP地址及其编号
        Map<String, Integer> ipToId = new HashMap<>();
        Map<Integer, String> idToIp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String ip = scanner.next();
            int id = scanner.nextInt();
            scanner.nextLine(); // 读取换行符
            ipToId.put(ip, id);
            idToIp.put(id, ip);
        }

        // 读取连通关系并构建邻接表
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            scanner.nextLine(); // 读取换行符
            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }

        // 读取需要判断连通性的IP地址对的数量q
        int q = scanner.nextInt();
        scanner.nextLine(); // 读取换行符

        // 处理每一对IP地址并输出结果
        for (int i = 0; i < q; i++) {
            String ip1 = scanner.next();
            String ip2 = scanner.next();
            scanner.nextLine(); // 读取换行符

            int id1 = ipToId.get(ip1);
            int id2 = ipToId.get(ip2);

            int result = bfs(graph, id1, id2);
            System.out.println(result);
        }
    }

    // 使用BFS算法计算两个节点之间的最短路径
    private static int bfs(Map<Integer, List<Integer>> graph, int start, int end) {
        if (start == end) {
            return 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                List<Integer> neighbors = graph.getOrDefault(current, new ArrayList<>());
                for (int neighbor : neighbors) {
                    if (neighbor == end) {
                        return level;
                    }
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            level++;
        }

        return -1; // 不连通时返回-1
    }
}
