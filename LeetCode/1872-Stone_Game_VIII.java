class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Step 1: Compute prefix sums in-place or using an array
        int[] prefix = new int[n];
        prefix[0] = stones[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }
        
        // Step 2: Base case - at least 2 stones must be picked (so up to index n-1)
        // dp represents the max score difference starting from index i
        int dp = prefix[n - 1];
        
        // Step 3: Work backwards from index n - 2 down to 1
        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(dp, prefix[i] - dp);
        }
        
        return dp;
    }
}
