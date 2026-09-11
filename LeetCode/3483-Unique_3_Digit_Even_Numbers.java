import java.util.HashSet;
import java.util.Set;

class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> uniqueEvenNumbers = new HashSet<>();
        int n = digits.length;

        // Iterate through all possible triplets using distinct indices
        for (int i = 0; i < n; i++) {
            // Hundreds digit cannot be zero
            if (digits[i] == 0) continue;

            for (int j = 0; j < n; j++) {
                if (i == j) continue; // Must be a different index

                for (int k = 0; k < n; k++) {
                    if (i == k || j == k) continue; // Must be a different index

                    // Units digit must be even
                    if (digits[k] % 2 == 0) {
                        int number = digits[i] * 100 + digits[j] * 10 + digits[k];
                        uniqueEvenNumbers.add(number);
                    }
                }
            }
        }

        return uniqueEvenNumbers.size();
    }
}
