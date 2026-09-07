class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        // endWith[i] stores the count of distinct non-empty subsequences ending with char ('a' + i)
        long[] endWith = new long[26];
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            // Total distinct non-empty subsequences formed so far
            long total = 0;
            for (int i = 0; i < 26; i++) {
                total = (total + endWith[i]) % MOD;
            }
            // Appending current character 'c' to all existing subsequences, plus 'c' alone (+1)
            endWith[idx] = (total + 1) % MOD;
        }
        
        long result = 0;
        for (int i = 0; i < 26; i++) {
            result = (result + endWith[i]) % MOD;
        }
        
        return (int) result;
    }
}
