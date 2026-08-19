import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Group reserved seats by row index
        Map<Integer, Integer> rowMasks = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            // Only columns 2 through 9 affect family seating
            if (col >= 2 && col <= 9) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << col));
            }
        }
        
        // Start by assuming every row has 2 valid family allocations
        int maxFamilies = n * 2;
        
        // Subtract allocations based on restrictions in rows with reserved seats
        for (int mask : rowMasks.values()) {
            boolean leftBlocked = (mask & (1 << 2 | 1 << 3 | 1 << 4 | 1 << 5)) != 0;
            boolean rightBlocked = (mask & (1 << 6 | 1 << 7 | 1 << 8 | 1 << 9)) != 0;
            boolean middleBlocked = (mask & (1 << 4 | 1 << 5 | 1 << 6 | 1 << 7)) != 0;
            
            if (!leftBlocked && !rightBlocked) {
                // Both sides open -> 0 lost families (2 total)
                continue;
            } else if (!leftBlocked || !rightBlocked || !middleBlocked) {
                // At least one valid 4-seat block open -> 1 lost family (1 total)
                maxFamilies -= 1;
            } else {
                // No valid block open -> 2 lost families (0 total)
                maxFamilies -= 2;
            }
        }
        
        return maxFamilies;
    }
}
