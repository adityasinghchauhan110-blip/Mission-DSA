import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();

        // Step 1: Collect coordinates of 1s in both images
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) ones1.add(new int[]{r, c});
                if (img2[r][c] == 1) ones2.add(new int[]{r, c});
            }
        }

        // Step 2: Count frequencies of each displacement vector
        Map<String, Integer> count = new HashMap<>();
        int maxOverlap = 0;

        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {
                int dr = p1[0] - p2[0];
                int dc = p1[1] - p2[1];
                String key = dr + "," + dc;

                int currentCount = count.getOrDefault(key, 0) + 1;
                count.put(key, currentCount);
                maxOverlap = Math.max(maxOverlap, currentCount);
            }
        }

        return maxOverlap;
    }
}
