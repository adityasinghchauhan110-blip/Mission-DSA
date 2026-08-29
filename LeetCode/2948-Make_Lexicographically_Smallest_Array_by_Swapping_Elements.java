import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Pair each number with its original index
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }
        
        // Sort pairs by value
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] result = new int[n];
        int i = 0;
        
        // Group connected elements where adjacent difference <= limit
        while (i < n) {
            int j = i;
            List<Integer> indices = new ArrayList<>();
            indices.add(pairs[i][1]);
            
            while (j + 1 < n && pairs[j + 1][0] - pairs[j][0] <= limit) {
                j++;
                indices.add(pairs[j][1]);
            }
            
            // Sort the original indices to place smallest values at smallest positions
            Collections.sort(indices);
            
            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = pairs[i + k][0];
            }
            
            i = j + 1;
        }
        
        return result;
    }
}
