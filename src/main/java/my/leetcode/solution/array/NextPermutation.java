package my.leetcode.solution.array;

/**
 * https://leetcode-cn.com/problems/next-permutation/
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int i = nums.length - 1;
        while (i >= 1 && nums[i - 1] >= nums[i]) {
            --i;
        }
        if (i == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        --i;

        int j = nums.length - 1;
        while (j > i && nums[j] <= nums[i]) {
            --j;
        }

        swap(nums, i, j);
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int left, int right) {
        for (int i = 0; i <= (right - left) / 2; ++i) {
            int p1 = left + i;
            int p2 = right - i;
            if (p1 != p2) {
                swap(nums, p1, p2);
            }
        }
    }
}
