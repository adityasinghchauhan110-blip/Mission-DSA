class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }

        int minIdx = 0;
        int maxIdx = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);

        // Option 1: Remove both from the front
        int removeBothFront = right + 1;

        // Option 2: Remove both from the back
        int removeBothBack = n - left;

        // Option 3: Remove one from front, one from back
        int removeBothSides = (left + 1) + (n - right);

        return Math.min(removeBothFront, Math.min(removeBothBack, removeBothSides));
    }
}
