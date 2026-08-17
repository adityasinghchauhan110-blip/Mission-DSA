class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        
        // Calculate prefix sums for O(1) range sum queries
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }
        
        // memo[i][j] stores the maximum score Alice can get from the subarray stoneValue[i...j]
        int[][] memo = new int[n][n];
        
        return solve(stoneValue, prefix, memo, 0, n - 1);
    }
    
    private int solve(int[] stoneValue, int[] prefix, int[][] memo, int left, int right) {
        // Base case: a single stone cannot be split, so score earned is 0
        if (left == right) {
            return 0;
        }
        
        // Return cached result if already computed
        if (memo[left][right] > 0) {
            return memo[left][right];
        }
        
        int maxScore = 0;
        
        // Try every possible partition point `k` where left subarray is [left...k]
        // and right subarray is [k+1...right]
        for (int k = left; k < right; k++) {
            int leftSum = prefix[k + 1] - prefix[left];
            int rightSum = prefix[right + 1] - prefix[k + 1];
            
            if (leftSum < rightSum) {
                // Bob throws away the right part (larger sum)
                int score = leftSum + solve(stoneValue, prefix, memo, left, k);
                maxScore = Math.max(maxScore, score);
            } else if (rightSum < leftSum) {
                // Bob throws away the left part (larger sum)
                int score = rightSum + solve(stoneValue, prefix, memo, k + 1, right);
                maxScore = Math.max(maxScore, score);
            } else {
                // Equal sums: Alice can choose to keep either left or right part
                int chooseLeft = leftSum + solve(stoneValue, prefix, memo, left, k);
                int chooseRight = rightSum + solve(stoneValue, prefix, memo, k + 1, right);
                maxScore = Math.max(maxScore, Math.max(chooseLeft, chooseRight));
            }
        }
        
        memo[left][right] = maxScore;
        return maxScore;
    }
}
