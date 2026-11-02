import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        // Sort the array to easily find the median
        Arrays.sort(nums);
        
        // The median is at the middle index
        int median = nums[nums.length / 2];
        
        int moves = 0;
        // Calculate the sum of absolute differences from the median
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        
        return moves;
    }
}
