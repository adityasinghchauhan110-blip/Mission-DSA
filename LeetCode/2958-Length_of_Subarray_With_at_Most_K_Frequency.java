import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            // Add the current element to the frequency map
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

            // If frequency of current element exceeds k, shrink the window from the left
            while (freq.get(nums[right]) > k) {
                freq.put(nums[left], freq.get(nums[left]) - 1);
                left++;
            }

            // Calculate the maximum length of a valid window
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
