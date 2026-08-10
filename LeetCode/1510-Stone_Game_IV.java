class Solution {
    public boolean winnerSquareGame(int n) {
        // dp[i] represents whether the current player can win with i stones remaining
        boolean[] dp = new boolean[n + 1];

        // Process states from 1 to n
        for (int i = 1; i <= n; i++) {
            // Try subtracting every possible non-zero square number
            for (int k = 1; k * k <= i; k++) {
                // If taking k*k stones leaves the opponent in a losing state, current player wins
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break; // No need to check further moves for state i
                }
            }
        }

        return dp[n];
    }
}
