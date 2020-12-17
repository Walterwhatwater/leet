package my.leetcode.solution.math;

import java.util.concurrent.locks.ReentrantLock;

public class Pow {
    public double myPow(double x, int n) {
        return n >= 0 ? doPow(x, n) : 1.0 / doPow(x, -(long) n);
    }

    private double doPow(double x, long n) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double square = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (n > 0) {
            if (n % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= square;
            }
            // 将贡献不断地平方
            square *= square;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            n /= 2;
        }
        return ans;
    }
}
