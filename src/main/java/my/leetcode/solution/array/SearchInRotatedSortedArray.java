package my.leetcode.solution.array;

/**
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < nums[nums.length - 1]) {
                // 后半部分有序
                if (nums[mid] < target && target <= nums[nums.length - 1]) {
                    // target在后半部分
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                // 前半部分有序
                if (nums[mid] > target && target >= nums[0]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;
    }
}
