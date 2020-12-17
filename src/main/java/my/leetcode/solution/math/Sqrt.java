package my.leetcode.solution.math;

/**
 * https://leetcode-cn.com/problems/sqrtx/
 */
public class Sqrt {
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }

        long left = 0;
        long right = x;

        while (left < right) {
            long mid = getMid(left, right);
            long square = mid * mid;
            if (square == x) {
                return (int) mid;
            } else if (square < x) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return (int) left;
    }

    private long getMid(long left, long right) {
        return (long) Math.ceil((left + right) / 2d);
    }
}
