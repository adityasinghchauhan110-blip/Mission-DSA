class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        // For n = 1 or 2, only the numbers themselves are possible.
        if (n < 3) {
            return n;
        }

        // Smallest power of 2 greater than n
        int ans = 1;
        while (ans <= n) {
            ans <<= 1;
        }

        return ans;
    }
}
