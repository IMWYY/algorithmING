package leetCode.dfsAndbfs;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs,
 * is it possible for you to finish all courses?
 * <p>
 * Example 1:
 * <p>
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * create by stephen on 2018/6/20
 */
public class Problem207 {

    /**
     * 利用邻接表来表示图
     * DFS算法 检查课程顺序的以来关系是不是一个有向无环图
     * 为了减少遍历的次数 用一个visitedCourse来记录被作为起始节点访问过的课程 不用再次遍历
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 1) return true;
        // map可以用一个数组来替换
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] pair : prerequisites) {
            if (map.get(pair[1]) == null) {
                List<Integer> values = new ArrayList<>();
                values.add(pair[0]);
                map.put(pair[1], values);
            } else {
                map.get(pair[1]).add(pair[0]);
            }
        }

        boolean[] visitedCourse = new boolean[numCourses];      // 记录被访问过的课程
        Arrays.fill(visitedCourse, false);
        boolean[] chosen = new boolean[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            if (!visitedCourse[i]) {        // 判断当前课程如果没有被访问过 则访问
                if (dfsHasRing(map, chosen, i, visitedCourse)) return false;
            }
        }
        return true;
    }

    private boolean dfsHasRing(Map<Integer, List<Integer>> map, boolean[] chosen, int curCourse, boolean[] visitedCourse) {
        visitedCourse[curCourse] = true;
        if (chosen[curCourse]) return true;
        if (map.get(curCourse) == null) return false;
        chosen[curCourse] = true;
        for (int c : map.get(curCourse)) {
            if (dfsHasRing(map, chosen, c, visitedCourse)) return true;
        }
        chosen[curCourse] = false; // 需要在退出之前将chosen数组重置
        return false;
    }


    /**
     * 用邻接矩阵来记录图的信息
     * 拓补排序的Kahn算法 用一个数组记录入度
     * 利用一个队列记录所有入度为0的节点
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        int[][] edges = new int[numCourses][numCourses];
        int[] inDegree = new int[numCourses];
        Arrays.fill(inDegree, 0);
        for (int[] pair : prerequisites) {          // 转换为邻接矩阵
            if (edges[pair[1]][pair[0]] == 0)
                inDegree[pair[0]]++;
            edges[pair[1]][pair[0]] = 1;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; ++i) {
            if (inDegree[i] == 0) queue.offer(i);       // 把所有入度为0的节点入队
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i = 0; i < numCourses; ++i) {
                if (edges[course][i] == 1) {        // 将所有以该顶点为起始顶点的边去除 并且更新入度的数组
                    inDegree[i]--;
                    edges[course][i] = 0;
                    if (inDegree[i] == 0) queue.offer(i);  // 把入度为0的节点入队
                }
            }
        }
        return numCourses == count;
    }

}
