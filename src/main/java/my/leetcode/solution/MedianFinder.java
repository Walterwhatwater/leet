package my.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

class MedianFinder {
    private List<Integer> data = new ArrayList<>();

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {

    }

    public void addNum(int num) {
        if (data.isEmpty()) {
            data.add(num);
        } else {
            boolean added = false;
            for (int i = 0; i < data.size(); ++i) {
                if (data.get(i) > num) {
                    data.add(i, num);
                    added = true;
                    break;
                }
            }
            if (!added) {
                data.add(num);
            }
        }
    }

    public double findMedian() {
        int mid = data.size() / 2;
        if (data.size() % 2 == 0) {
            return (data.get(mid - 1) + data.get(mid)) / (double) 2;
        } else {
            return data.get(mid);
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
