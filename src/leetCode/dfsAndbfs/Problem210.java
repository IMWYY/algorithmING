package leetCode.dfsAndbfs;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs,
 * return the ordering of courses you should take to finish all courses.
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 * <p>
 * Example 1:
 * <p>
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 * course 0. So the correct course order is [0,1] .
 * <p>
 * create by stephen on 2018/10/19
 */
public class Problem210 {

    /**
     * 拓补排序 Kahn算法 BFS
     * 参考Problem207
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[0];

        // init Adjacency matrix
        int[] linkCount = new int[numCourses];
        Arrays.fill(linkCount, 0);
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) adj.add(new ArrayList<>());

        for (int[] pre : prerequisites) {
            adj.get(pre[1]).add(pre[0]);
            linkCount[pre[0]]++;
        }
        int[] res = new int[linkCount.length];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < linkCount.length; ++i) {
            if (linkCount[i] == 0) queue.add(i);
        }
        int visitedCount = 0;
        while (!queue.isEmpty()) {
            int ele = queue.poll();
            res[visitedCount++] = ele;
            for (int i : adj.get(ele)) {
                linkCount[i]--;
                if (linkCount[i] == 0) queue.add(i);
            }
        }

        return visitedCount == linkCount.length ? res : new int[0];
    }

    /**
     * DFS算法
     * 需要注意的是DFS完了是逆序的 需要用栈转换一下
     */
    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[0];
        // init Adjacency matrix
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) graph.add(new ArrayList<>());
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
        }

        int[] visited = new int[numCourses];
        Arrays.fill(visited, 0); //0 -> unvisited, 1 -> visiting, 2 -> visited

        Stack<Integer> dfsResult = new Stack<>();
        for (int i = 0; i < numCourses; ++i) {
            if (!solveByDfs(graph, visited, dfsResult, i)) return new int[0];
        }

        if (dfsResult.size() != numCourses) return new int[0];
        int[] res = new int[numCourses];
        int i = 0;
        while (!dfsResult.isEmpty()) {
            res[i++] = dfsResult.pop();
        }
        return res;
    }

    private boolean solveByDfs(List<List<Integer>> graph, int[] visited, Stack<Integer> dfsResult, int i) {
        if (visited[i] == 2) return true;
        if (visited[i] == 1) return false;

        visited[i] = 1;
        for (int j : graph.get(i)) {
            if (!solveByDfs(graph, visited, dfsResult, j)) return false;
        }
        visited[i] = 2;
        dfsResult.push(i);
        return true;
    }
}
