package my.leetcode.solution.sort;

/**
 * https://leetcode-cn.com/problems/sort-an-array/
 */
public class MergeSort {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int start, int end) {
        if (start < end) {//当子序列中只有一个元素时结束递归
            int mid = (start + end) / 2;//划分子序列
            mergeSort(nums, start, mid);//对左侧子序列进行递归排序
            mergeSort(nums, mid + 1, end);//对右侧子序列进行递归排序
            merge(nums, start, mid, end);//合并
        }
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] tmp = new int[nums.length];//辅助数组
        int p1 = left;
        int p2 = mid + 1;
        int k = left; //p1、p2是检测指针，k是存放指针

        while (p1 <= mid && p2 <= right) {
            if (nums[p1] <= nums[p2]) {
                tmp[k++] = nums[p1++];
            } else {
                tmp[k++] = nums[p2++];
            }
        }

        while (p1 <= mid) {
            tmp[k++] = nums[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        }
        while (p2 <= right) {
            tmp[k++] = nums[p2++];//同上
        }

        //复制回原素组
        for (int i = left; i <= right; i++) {
            nums[i] = tmp[i];
        }
    }
}
