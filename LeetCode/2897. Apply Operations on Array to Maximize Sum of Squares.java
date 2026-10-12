import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[31];
        
        // Count the set bits at each position across all numbers
        for (int num : nums) {
            for (int bit = 0; bit <= 30; bit++) {
                if ((num & (1 << bit)) != 0) {
                    bitCount[bit]++;
                }
            }
        }
        
        long totalSum = 0;
        long MOD = 1_000_000_007;
        
        // Greedily construct k numbers to maximize the sum of squares
        for (int i = 0; i < k; i++) {
            int currentNum = 0;
            for (int bit = 0; bit <= 30; bit++) {
                if (bitCount[bit] > 0) {
                    currentNum |= (1 << bit);
                    bitCount[bit]--;
                }
            }
            totalSum = (totalSum + (long) currentNum * currentNum) % MOD;
        }
        
        return (int) totalSum;
    }
}
