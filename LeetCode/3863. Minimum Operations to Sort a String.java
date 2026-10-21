class Solution {
    public int minOperations(String s) {
        int n = s.length();
        if (n <= 1) return 0;

        // 1. Check if already sorted
        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;

        // If n == 2 and not sorted, any proper substring has length 1,
        // which cannot alter the string.
        if (n == 2) return -1;

        // Find min and max characters
        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if (c < minChar) minChar = c;
            if (c > maxChar) maxChar = c;
        }

        // Count occurrences of minChar and maxChar
        int countMin = 0, countMax = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == minChar) countMin++;
            if (s.charAt(i) == maxChar) countMax++;
        }

        // 1 operation: min character is already at index 0 
        // OR max character is already at index n - 1
        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }

        // 2 operations: 
        // A min character exists anywhere other than index n - 1, OR
        // A max character exists anywhere other than index 0
        // (This allows placing min at index 0 in op 1, or max at n - 1 in op 1)
        boolean minElsewhere = false;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == minChar) {
                minElsewhere = true;
                break;
            }
        }

        boolean maxElsewhere = false;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == maxChar) {
                maxElsewhere = true;
                break;
            }
        }

        if (minElsewhere || maxElsewhere) {
            return 2;
        }

        // 3 operations: all min characters are at index n - 1
        // AND all max characters are at index 0.
        // For n >= 3, this takes 3 operations.
        return 3;
    }
}
