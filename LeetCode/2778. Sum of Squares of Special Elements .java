class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        // Iterate through the array (using 1-based indexing for i)
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                // Corresponding 0-based index is i - 1
                int val = nums[i - 1];
                sum += val * val;
            }
        }
        
        return sum;
    }
}
