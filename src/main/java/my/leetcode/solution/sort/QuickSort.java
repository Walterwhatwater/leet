package my.leetcode.solution.sort;

/**
 * https://leetcode-cn.com/problems/sort-an-array/
 */
public class QuickSort {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int i = getIndex(nums, low, high);

            quickSort(nums, low, i - 1);
            quickSort(nums, i + 1, high);
        }
    }

    private int getIndex(int[] nums, int low, int high) {
        int target = nums[low];

        while (low < high) {
            while (low < high && nums[high] > target) {
                high--;
            }
            nums[low] = nums[high];

            while (low < high && nums[low] <= target) {
                low++;
            }
            nums[high] = nums[low];
        }

        nums[low] = target;
        return low;
    }
}
