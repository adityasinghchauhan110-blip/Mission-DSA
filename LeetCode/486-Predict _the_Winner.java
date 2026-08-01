class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        // dp[i] will store the maximum relative score advantage a player can get
        // from the subarray nums[i...j]
        int[] dp = new int[n];
        
        // Base case: Subarrays of length 1 (i == j)
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
        }
        
        // Build DP table for subarrays of increasing lengths
        for (int diff = 1; diff < n; diff++) {
            for (int i = 0; i < n - diff; i++) {
                int j = i + diff;
                // If the player chooses nums[i], the remaining subarray is nums[i+1...j].
                // If the player chooses nums[j], the remaining subarray is nums[i...j-1].
                dp[i] = Math.max(nums[i] - dp[i + 1], nums[j] - dp[i]);
            }
        }
        
        // If Player 1 can achieve a non-negative net score advantage for nums[0...n-1]
        return dp[0] >= 0;
    }
}
