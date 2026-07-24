import java.util.HashSet;
import java.util.Set;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Step 1: Precompute all unique XOR pair values (nums[i] ^ nums[j])
        // using boolean array for O(1) direct bit indexing up to max XOR range.
        boolean[] hasPair = new boolean[2048]; // Max XOR of values <= 1500 fits within 2047
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                hasPair[nums[i] ^ nums[j]] = true;
            }
        }
        
        // Step 2: Combine all unique XOR pair values with every single element
        Set<Integer> uniqueTripletXors = new HashSet<>();
        
        for (int pairXor = 0; pairXor < hasPair.length; pairXor++) {
            if (!hasPair[pairXor]) continue;
            
            for (int num : nums) {
                uniqueTripletXors.add(pairXor ^ num);
            }
        }
        
        return uniqueTripletXors.size();
    }
}
