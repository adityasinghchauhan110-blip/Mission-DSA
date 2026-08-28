class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }

        // A palindrome can have at most one character with odd frequency
        if (oddCount > 1 || (n % 2 == 0 && oddCount > 0)) {
            return "";
        }

        int m = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        // Case 1: Check if matching the first m characters of target yields a palindrome > target
        int[] targetHalfCount = new int[26];
        boolean canMatchTargetPrefix = true;
        for (int i = 0; i < m; i++) {
            int c = target.charAt(i) - 'a';
            targetHalfCount[c]++;
            if (targetHalfCount[c] > halfCount[c]) {
                canMatchTargetPrefix = false;
                break;
            }
        }

        if (canMatchTargetPrefix) {
            String firstHalf = target.substring(0, m);
            String candidate = buildPalindrome(firstHalf, midChar, n % 2 != 0);
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        // Case 2: Find the longest prefix match of length i (m - 1 down to 0)
        // Prefix target[0..i-1] must be valid, then pick the smallest character c > target[i]
        int[] prefixCount = new int[26];
        int maxValidPrefix = 0;
        while (maxValidPrefix < m) {
            int c = target.charAt(maxValidPrefix) - 'a';
            if (prefixCount[c] + 1 <= halfCount[c]) {
                prefixCount[c]++;
                maxValidPrefix++;
            } else {
                break;
            }
        }

        for (int i = maxValidPrefix; i >= 0; i--) {
            // Recompute counts used up to index i - 1
            int[] currentUsed = new int[26];
            for (int k = 0; k < i; k++) {
                currentUsed[target.charAt(k) - 'a']++;
            }

            if (i < m) {
                int targetChar = target.charAt(i) - 'a';
                for (int c = targetChar + 1; c < 26; c++) {
                    if (halfCount[c] - currentUsed[c] > 0) {
                        // Valid character found; build the first half
                        StringBuilder firstHalf = new StringBuilder();
                        for (int k = 0; k < i; k++) {
                            firstHalf.append(target.charAt(k));
                        }
                        firstHalf.append((char) ('a' + c));
                        currentUsed[c]++;

                        // Append the remaining available characters in ascending order
                        for (int rem = 0; rem < 26; rem++) {
                            int available = halfCount[rem] - currentUsed[rem];
                            while (available-- > 0) {
                                firstHalf.append((char) ('a' + rem));
                            }
                        }

                        return buildPalindrome(firstHalf.toString(), midChar, n % 2 != 0);
                    }
                }
            }
        }

        return "";
    }

    private String buildPalindrome(String firstHalf, char midChar, boolean isOdd) {
        StringBuilder sb = new StringBuilder(firstHalf);
        if (isOdd) {
            sb.append(midChar);
        }
        for (int i = firstHalf.length() - 1; i >= 0; i--) {
            sb.append(firstHalf.charAt(i));
        }
        return sb.toString();
    }
}
