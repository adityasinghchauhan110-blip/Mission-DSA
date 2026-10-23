class Solution {
    public int sortPermutation(int[] nums) {
        // Initialize ans with -1 (all bits set to 1 in 32-bit two's complement integer representation)
        int ans = -1;
        
        for (int i = 0; i < nums.length; i++) {
            // Check if the current element is misplaced
            if (nums[i] != i) {
                ans &= nums[i];
            }
        }
        
        // If array is already sorted, ans remains -1, so return 0
        return Math.max(ans, 0);
    }
}
