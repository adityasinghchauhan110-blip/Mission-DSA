class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int p = 0; // position of 0
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                p = i;
                break;
            }
        }

        boolean asc = true, desc = true;
        for (int i = 0; i < n; i++) {
            int next = nums[(i + 1) % n];
            if (next != (nums[i] + 1) % n) asc = false;
            if (next != (nums[i] - 1 + n) % n) desc = false;
        }

        int ans = Integer.MAX_VALUE;

        if (asc) {
            if (p == 0) {
                ans = 0;
            } else {
                // rotate left p times, or reverse + rotate left (n-p) + reverse
                ans = Math.min(p, n - p + 2);
            }
        }

        if (desc) {
            // reverse, then rotate left (n-1-p) times -> n - p total
            // or rotate left (p+1) times, then reverse -> p + 2 total
            ans = Math.min(ans, Math.min(n - p, p + 2));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
