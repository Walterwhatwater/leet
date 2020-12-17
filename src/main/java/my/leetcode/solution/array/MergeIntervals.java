package my.leetcode.solution.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/merge-intervals/
 */
public class MergeIntervals {
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }

        int max = Integer.MAX_VALUE / 10;
        int maxSingle = Integer.MAX_VALUE % 10;
        int min = Integer.MIN_VALUE / 10;
        int minSingle = Integer.MIN_VALUE % 10;
        int result = 0;
        while (x != 0) {
            int current = x % 10;

            if (result > max
                    || (result == max && current > maxSingle)) {
                return 0;
            } else if (result < min
                    || (result == min && current < minSingle)) {
                return 0;
            } else {
                result = result * 10 + current;
            }

            x = x / 10;
        }

        return result;
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][2];
        }

        List<int[]> result = new LinkedList<>();
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        for (int i = 0; i < intervals.length; ++i) {
            int left = intervals[i][0];
            int right = intervals[i][1];

            while (i < intervals.length - 1 && intervals[i + 1][0] <= right) {
                right = Math.max(right, intervals[i + 1][1]);
                ++i;
            }

            result.add(new int[]{left, right});
        }

        return result.toArray(new int[0][]);
    }
}
