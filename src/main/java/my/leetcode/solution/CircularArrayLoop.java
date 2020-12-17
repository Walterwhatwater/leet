package my.leetcode.solution;

/**
 * https://leetcode-cn.com/problems/circular-array-loop/
 */
public class CircularArrayLoop {
    private void setZero(int[] nums, int i) {
        int next;
        while (true){
            next = Math.floorMod(i + nums[i], nums.length);
            if (nums[next] == 0 || nums[i] * nums[next] < 0) {
                nums[i] = 0;
                break;
            }

            nums[i] = 0;
            i = next;
        }
    }

    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                continue;
            }
            int slow = i, fast = i;
            int lastSlow, lastFast;
            while (true) {
                lastSlow = slow;
                slow = Math.floorMod(slow + nums[slow], n);
                if (nums[lastSlow] * nums[slow] < 0 || nums[slow] == 0 || slow == lastSlow) {
                    setZero(nums, i);
                    break;
                }

                lastFast = fast;
                fast = Math.floorMod(fast + nums[fast], n);
                if (nums[lastFast] * nums[fast] < 0 || nums[fast] == 0 || fast == lastFast) {
                    setZero(nums, i);
                    break;
                }

                lastFast = fast;
                fast = Math.floorMod(fast + nums[fast], n);
                if (nums[lastFast] * nums[fast] < 0 || nums[fast] == 0 || fast == lastFast) {
                    setZero(nums, i);
                    break;
                }

                if (fast == slow) {
                    return true;
                }
            }
        }
        return false;
    }
}
