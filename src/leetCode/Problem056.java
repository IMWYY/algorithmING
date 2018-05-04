package leetCode;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * Example 1:
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * <p>
 * Example 2:
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considerred overlapping.
 * <p>
 * create by stephen on 2018/5/4
 */
public class Problem056 {

    private class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    /**
     * 排序后逐个加入结果集合
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) return intervals;
        List<Interval> result = new ArrayList<>();
        intervals.sort(Comparator.comparingInt(o -> o.start));
        result.add(intervals.get(0));
        for (Interval interval : intervals) {
            Interval temp = result.get(result.size()-1);
            if (temp.end >= interval.start) {
                temp.end = Math.max(temp.end, interval.end);
            } else {
                result.add(interval);
            }
        }

        return result;
    }
}
