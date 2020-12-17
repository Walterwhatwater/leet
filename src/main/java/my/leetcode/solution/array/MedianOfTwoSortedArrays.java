package my.leetcode.solution.array;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 */
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        if ((n + m) % 2 == 1) {
            int mid = (n + m) / 2 + 1;
            return getKth(nums1, 0, n - 1, nums2, 0, m - 1, mid);
        } else {
            int left = (n + m) / 2;
            int right = left + 1;

            return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) / (double) 2;
        }
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int length1 = end1 - start1 + 1;
        int length2 = end2 - start2 + 1;

        if (length1 > length2) {
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        }

        if (length1 == 0) {
            return nums2[start2 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int p1 = start1 + Math.min(length1, k / 2) - 1;
        int p2 = start2 + Math.min(length2, k / 2) - 1;

        if (nums1[p1] <= nums2[p2]) {
            return getKth(nums1, p1 + 1, end1, nums2, start2, end2, k - (p1 - start1 + 1));
        } else {
            return getKth(nums1, start1, end1, nums2, p2 + 1, end2, k - (p2 - start2 + 1));
        }
    }
}
