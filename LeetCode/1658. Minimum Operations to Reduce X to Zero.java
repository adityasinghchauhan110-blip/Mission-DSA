class Solution {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int target = totalSum - x;

        // If target < 0, total sum of elements is smaller than x, impossible.
        if (target < 0) {
            return -1;
        }

        // If target is 0, we must take all elements.
        if (target == 0) {
            return nums.length;
        }

        int currentSum = 0;
        int maxLen = -1;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];

            // Shrink window if currentSum exceeds target
            while (currentSum > target && left <= right) {
                currentSum -= nums[left];
                left++;
            }

            // Update maxLen if current window sum equals target
            if (currentSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen == -1 ? -1 : nums.length - maxLen;
    }
}
