class Solution {
    private int[][] memo;
    private int[] suffixSum;

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        memo = new int[n][n + 1];
        suffixSum = new int[n];

        // Precompute suffix sums to get total stones from index i to end in O(1) time
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        return getOptimalStones(0, 1, n);
    }

    private int getOptimalStones(int i, int M, int n) {
        // Base case: If remaining piles can all be taken, take all of them
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }

        if (memo[i][M] != 0) {
            return memo[i][M];
        }

        int maxStones = 0;

        // Try taking X piles where 1 <= X <= 2 * M
        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);
            // Current player gets remaining sum minus what opponent can get optimally
            int stones = suffixSum[i] - getOptimalStones(i + X, nextM, n);
            maxStones = Math.max(maxStones, stones);
        }

        memo[i][M] = maxStones;
        return maxStones;
    }
}
