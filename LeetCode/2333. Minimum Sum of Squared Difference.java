class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + (long) k2;
        int[] d = new int[n];
        long sumD = 0;
        int maxD = 0;
        
        // Step 1: Calculate absolute differences
        for (int i = 0; i < n; i++) {
            d[i] = Math.abs(nums1[i] - nums2[i]);
            sumD += d[i];
            maxD = Math.max(maxD, d[i]);
        }
        
        // If total budget can cover all differences, min sum is 0
        if (sumD <= totalK) {
            return 0;
        }
        
        // Step 2: Binary search for the optimal maximum difference threshold
        int left = 0, right = maxD;
        while (left < right) {
            int mid = left + (right - left) / 2;
            long ops = 0;
            for (int v : d) {
                if (v > mid) {
                    ops += (v - mid);
                }
            }
            if (ops <= totalK) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        // Step 3: Apply the threshold reduction
        for (int i = 0; i < n; i++) {
            if (d[i] > left) {
                totalK -= (d[i] - left);
                d[i] = left;
            }
        }
        
        // Step 4: Distribute any remaining budget on elements equal to the threshold
        for (int i = 0; i < n && totalK > 0; i++) {
            if (d[i] == left) {
                d[i]--;
                totalK--;
            }
        }
        
        // Step 5: Calculate final sum of squared differences using long to prevent overflow
        long result = 0;
        for (int v : d) {
            result += (long) v * v;
        }
        
        return result;
    }
}
