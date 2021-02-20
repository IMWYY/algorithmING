package leetCode.dfsAndbfs;

import java.util.*;

public class Problem207 {

    // store the graph in the format of adjacent list
    // then perform a topological sort
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] edges = new int[numCourses][numCourses];
        int[] inDegree = new int[numCourses];
        Arrays.fill(inDegree, 0);
        for (int[] pair : prerequisites) {
            if (edges[pair[1]][pair[0]] == 0)
                inDegree[pair[0]]++;
            edges[pair[1]][pair[0]] = 1;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; ++i) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i = 0; i < numCourses; ++i) {
                if (edges[course][i] == 1) {
                    inDegree[i]--;
                    edges[course][i] = 0;
                    if (inDegree[i] == 0) queue.offer(i);
                }
            }
        }
        return numCourses == count;
    }
}
