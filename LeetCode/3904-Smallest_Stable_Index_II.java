class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return -1;

        // prefMax[i] = max element in nums[0...i]
        int[] prefMax = new int[n];
        prefMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefMax[i] = Math.max(prefMax[i - 1], nums[i]);
        }

        // suffMin[i] = min element in nums[i...n-1]
        int[] suffMin = new int[n];
        suffMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
        }

        // Find the first index where prefMax[i] - suffMin[i] <= k
        for (int i = 0; i < n; i++) {
            if (prefMax[i] - suffMin[i] <= k) {
                return i;
            }
        }

        return -1;
    }
}
