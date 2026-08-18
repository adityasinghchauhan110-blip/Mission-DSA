import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // Case 1: k equals array length -> Return the overall max element
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }

        // Case 2: k == 1 -> Return the max element that occurs exactly once in total
        if (k == 1) {
            Map<Integer, Integer> freq = new HashMap<>();
            for (int num : nums) {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }
            int maxVal = -1;
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                if (entry.getValue() == 1) {
                    maxVal = Math.max(maxVal, entry.getKey());
                }
            }
            return maxVal;
        }

        // Case 3: 1 < k < n -> Only nums[0] or nums[n-1] can appear in exactly 1 subarray
        Map<Integer, Integer> totalFreq = new HashMap<>();
        for (int num : nums) {
            totalFreq.put(num, totalFreq.getOrDefault(num, 0) + 1);
        }

        int result = -1;
        // Check nums[0]: valid only if it does not appear anywhere else in the array
        if (totalFreq.get(nums[0]) == 1) {
            result = Math.max(result, nums[0]);
        }
        // Check nums[n - 1]: valid only if it does not appear anywhere else in the array
        if (totalFreq.get(nums[n - 1]) == 1) {
            result = Math.max(result, nums[n - 1]);
        }

        return result;
    }
}
