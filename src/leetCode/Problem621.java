package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where
 * different letters represent different tasks.Tasks could be done without original order.
 * Each task could be done in one interval.
 * For each interval, CPU could finish one task or just be idle.
 * <p>
 * However, there is a non-negative cooling interval n that means between two same tasks,
 * there must be at least n intervals that CPU are doing different tasks or just be idle.
 * <p>
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 * <p>
 * Example 1:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 * <p>
 * create by stephen on 2018/4/28
 */
public class Problem621 {

    /**
     * 数组排序后 每一个interval选择出现次数最多的n个任务
     */
    public int leastInterval1(char[] tasks, int n) {
        if (tasks.length == 0) return 0;
        int[] map = new int[26];
        Arrays.fill(map, 0);
        for (char task : tasks) {
            map[task - 'A']++;
        }
        Arrays.sort(map);

        int result = 0;
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0) break;
                if (i < 26 && map[25 - i] > 0) {
                    map[25 - i]--;
                }
                i++;
                result++;
            }

            Arrays.sort(map);
        }

        return result;
    }

    /**
     * 方法同上 优化的方向是利用最大堆每次获取出现频率最高的任务
     */
    public int leastInterval2(char[] tasks, int n) {
        if (tasks.length == 0) return 0;
        int[] map = new int[26];
        Arrays.fill(map, 0);
        for (char task : tasks) {
            map[task - 'A']++;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        for (int i : map) {
            if (i > 0) maxHeap.offer(i);
        }

        int result = 0;
        List<Integer> tempList = new ArrayList<>();

        while (!maxHeap.isEmpty()) {
            tempList.clear();
            int i = 0, temp;
            while (i <= n) {
                if (maxHeap.isEmpty()) break;
                temp = maxHeap.poll();
                if (temp > 1) {
                    tempList.add(temp - 1);
                }
                i++;
                result++;
            }

            for (int j : tempList) {
                if (j > 0) maxHeap.offer(i);
            }
        }

        return result;
    }

    /**
     * 以出现次数最多的task为基准，填充idle task。
     * 最后结果为tasks + idle task
     */
    public int leastInterval3(char[] tasks, int n) {
        if (tasks.length == 0) return 0;
        int[] map = new int[26];
        Arrays.fill(map, 0);
        for (char task : tasks) {
            map[task - 'A']++;
        }
        Arrays.sort(map);
        int idle = (map[25] - 1) * n;
        for (int i = 24; i >= 0 && map[i] > 0; --i) {
            idle -= Math.max(map[25], map[i]);
        }

        return idle > 0 ? idle + tasks.length : tasks.length;
    }
}
