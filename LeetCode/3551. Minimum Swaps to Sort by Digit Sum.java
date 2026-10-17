import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;

        // Store [digitSum, value, originalIndex]
        int[][] elements = new int[n][3];
        for (int i = 0; i < n; i++) {
            elements[i][0] = getDigitSum(nums[i]);
            elements[i][1] = nums[i];
            elements[i][2] = i;
        }

        // Sort by digit sum ascending; tie-break by original value ascending
        Arrays.sort(elements, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Map original indices to target sorted positions
        int[] targetPos = new int[n];
        for (int i = 0; i < n; i++) {
            int originalIdx = elements[i][2];
            targetPos[originalIdx] = i;
        }

        // Cycle decomposition
        boolean[] visited = new boolean[n];
        int totalSwaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || targetPos[i] == i) {
                continue;
            }

            int cycleLength = 0;
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = targetPos[curr];
                cycleLength++;
            }

            if (cycleLength > 1) {
                totalSwaps += (cycleLength - 1);
            }
        }

        return totalSwaps;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
