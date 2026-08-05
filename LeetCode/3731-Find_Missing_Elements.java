import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        // Step 1: Sort the array to place numbers in ascending order
        Arrays.sort(nums);

        List<Integer> result = new ArrayList<>();

        // Step 2: Iterate through adjacent elements to find missing values
        for (int i = 1; i < nums.length; i++) {
            for (int val = nums[i - 1] + 1; val < nums[i]; val++) {
                result.add(val);
            }
        }

        return result;
    }
}
