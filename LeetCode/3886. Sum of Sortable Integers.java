import java.util.ArrayList;
import java.util.List;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int totalSum = 0;

        // Find all divisors of n
        List<Integer> divisors = new ArrayList<>();
        for (int d = 1; d * d <= n; d++) {
            if (n % d == 0) {
                divisors.add(d);
                if (d * d != n) {
                    divisors.add(n / d);
                }
            }
        }

        // Check each divisor
        for (int k : divisors) {
            if (isSortable(nums, n, k)) {
                totalSum += k;
            }
        }

        return totalSum;
    }

    private boolean isSortable(int[] nums, int n, int k) {
        int prevMax = -1;

        for (int start = 0; start < n; start += k) {
            int drops = 0;
            int blockMin = nums[start];
            int blockMax = nums[start];

            for (int j = 0; j < k; j++) {
                int curr = nums[start + j];
                int next = nums[start + (j + 1) % k];

                if (curr > next) {
                    drops++;
                    if (drops > 1) {
                        return false;
                    }
                }

                if (curr < blockMin) blockMin = curr;
                if (curr > blockMax) blockMax = curr;
            }

            // The maximum element of the previous block must not exceed
            // the minimum element of the current block
            if (prevMax != -1 && prevMax > blockMin) {
                return false;
            }

            prevMax = blockMax;
        }

        return true;
    }
}
