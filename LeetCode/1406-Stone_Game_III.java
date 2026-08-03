class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        
        // dp[i] stores the score advantage of the current player starting from index i
        // Using a 4-element array for O(1) space optimization
        int[] dp = new int[4]; 
        
        for (int i = n - 1; i >= 0; i--) {
            int maxAdvantage = Integer.MIN_VALUE;
            int currentSum = 0;
            
            // Try picking 1, 2, or 3 stones
            for (int k = 1; k <= 3 && i + k <= n; k++) {
                currentSum += stoneValue[i + k - 1];
                
                // Score gained minus opponent's best advantage from index (i + k)
                int opponentAdvantage = dp[(i + k) % 4];
                maxAdvantage = Math.max(maxAdvantage, currentSum - opponentAdvantage);
            }
            
            dp[i % 4] = maxAdvantage;
        }
        
        // Alice starts at index 0
        int aliceAdvantage = dp[0];
        
        if (aliceAdvantage > 0) {
            return "Alice";
        } else if (aliceAdvantage < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}
