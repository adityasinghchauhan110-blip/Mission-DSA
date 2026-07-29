import java.util.*;

class Solution {
    private static final int MAX_K = 1_000_001; // Capped to avoid integer overflow

    public String smallestPalindrome(String s, int k) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Half counts for the first half of the palindrome
        int[] halfCount = new int[26];
        char midChar = 0;
        int halfLen = 0;

        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
            halfLen += halfCount[i];
            if (count[i] % 2 != 0) {
                midChar = (char) ('a' + i);
            }
        }

        // Check if total distinct palindromes is less than k
        long totalPerms = countArrangements(halfCount);
        if (totalPerms < k) {
            return "";
        }

        StringBuilder leftHalf = new StringBuilder();

        // Build the first half position by position
        for (int pos = 0; pos < halfLen; pos++) {
            for (int i = 0; i < 26; i++) {
                if (halfCount[i] == 0) continue;

                // Temporarily place character 'a' + i
                halfCount[i]--;
                long arrangements = countArrangements(halfCount);

                if (arrangements >= k) {
                    leftHalf.append((char) ('a' + i));
                    break; // Position fixed, move to next character
                } else {
                    k -= arrangements; // Skip these permutations
                    halfCount[i]++;   // Backtrack
                }
            }
        }

        // Build the full palindrome: leftHalf + midChar (if any) + reversed(leftHalf)
        StringBuilder result = new StringBuilder(leftHalf);
        if (midChar != 0) {
            result.append(midChar);
        }
        StringBuilder rightHalf = new StringBuilder(leftHalf).reverse();
        result.append(rightHalf);

        return result.toString();
    }

    // Helper to calculate total distinct arrangements given character counts
    private long countArrangements(int[] count) {
        int total = 0;
        for (int freq : count) {
            total += freq;
        }

        long res = 1;
        for (int freq : count) {
            if (freq == 0) continue;
            res = mult(res, nCk(total, freq));
            total -= freq;
        }
        return res;
    }

    // Combination n Choose k with saturation to avoid overflow
    private long nCk(int n, int k) {
        if (k < 0 || k > n) return 0;
        k = Math.min(k, n - k);
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;
            if (res >= MAX_K) return MAX_K;
        }
        return res;
    }

    // Multiplies two values with saturation at MAX_K
    private long mult(long a, long b) {
        if (a == 0 || b == 0) return 0;
        if (a >= MAX_K || b >= MAX_K || a * b >= MAX_K) {
            return MAX_K;
        }
        return a * b;
    }
}
