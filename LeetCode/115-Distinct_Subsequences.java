class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        // dp[j] represents the number of distinct subsequences of s that match t[0...j-1]
        // Using double or long array to avoid overflow before the final return type,
        // although LeetCode 115 guarantees the answer fits in a 32-bit signed integer.
        int[] dp = new int[n + 1];

        // An empty target t ("") can always be formed by deleting all characters of s (1 way).
        dp[0] = 1;

        for (int i = 1; i <= m; i++) {
            char sc = s.charAt(i - 1);
            // Traverse backwards to update in-place without overwriting previous row values
            for (int j = n; j >= 1; j--) {
                if (sc == t.charAt(j - 1)) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }

        return dp[n];
    }
}
