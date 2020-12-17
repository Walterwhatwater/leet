package my.leetcode.solution.array;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 */
public class KthLargestInArray {
    public int findKthLargest(int[] nums, int k) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int i = getIndex(nums, low, high);
            if (i == k - 1) {
                return nums[i];
            } else if (i > k - 1) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }

        return nums[low];
    }

    private int getIndex(int[] nums, int low, int high) {
        int target = nums[low];

        while (low < high) {
            while (low < high && nums[high] < target) {
                --high;
            }
            nums[low] = nums[high];

            while (low < high && nums[low] >= target) {
                ++low;
            }
            nums[high] = nums[low];
        }

        nums[low] = target;
        return low;
    }
}
