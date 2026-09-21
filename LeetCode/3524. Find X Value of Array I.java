class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] result = new long[k];
        
        // dp[v] stores the number of valid subarrays ending at the current index 
        // whose product modulo k is equal to v.
        long[] dp = new long[k];
        
        for (int num : nums) {
            int currentMod = num % k;
            long[] nextDp = new long[k];
            
            // A single element subarray starting and ending at the current element
            nextDp[currentMod]++;
            
            // Extend existing subarrays with the current element
            for (int prevMod = 0; prevMod < k; prevMod++) {
                if (dp[prevMod] > 0) {
                    int newMod = (int) (((long) prevMod * currentMod) % k);
                    nextDp[newMod] += dp[prevMod];
                }
            }
            
            // Add the subarray counts ending at the current element to the global result
            for (int mod = 0; mod < k; mod++) {
                result[mod] += nextDp[mod];
            }
            
            dp = nextDp;
        }
        
        return result;
    }
}
