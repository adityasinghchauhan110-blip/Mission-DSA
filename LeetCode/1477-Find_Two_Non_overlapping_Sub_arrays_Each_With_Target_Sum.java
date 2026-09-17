class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        // minLen[i] stores the minimum length of a subarray with sum == target in arr[0...i]
        int[] minLen = new int[n];
        
        int ans = Integer.MAX_VALUE;
        int currentSum = 0;
        int l = 0;
        int minSoFar = Integer.MAX_VALUE;

        for (int r = 0; r < n; r++) {
            currentSum += arr[r];

            // Shrink window if sum exceeds target
            while (currentSum > target) {
                currentSum -= arr[l];
                l++;
            }

            // Found a valid subarray arr[l...r]
            if (currentSum == target) {
                int len = r - l + 1;

                // Check if a valid non-overlapping subarray exists before index 'l'
                if (l > 0 && minLen[l - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, len + minLen[l - 1]);
                }

                // Update the minimum length seen up to current index 'r'
                minSoFar = Math.min(minSoFar, len);
            }

            minLen[r] = minSoFar;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
