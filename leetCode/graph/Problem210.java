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
 */
public class Problem210 {

    // Topological sort, see Problem207
    public int[] findOrder(int numCourses, int[][] prerequisites) { if (numCourses == 0) return new int[0];
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
}
