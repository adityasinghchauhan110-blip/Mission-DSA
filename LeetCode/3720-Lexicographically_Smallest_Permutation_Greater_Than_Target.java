import java.util.Arrays;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        if (target.length() != n) {
            return "";
        }

        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Try to match prefix of length `len` from target (len from n down to 0)
        for (int len = n; len >= 0; len--) {
            int[] currentCount = count.clone();
            boolean possible = true;

            // Check if we can form the prefix target[0 ... len - 1]
            for (int i = 0; i < len; i++) {
                int charIdx = target.charAt(i) - 'a';
                if (currentCount[charIdx] > 0) {
                    currentCount[charIdx]--;
                } else {
                    possible = false;
                    break;
                }
            }

            if (!possible) {
                continue;
            }

            // If len == n, the permutation would equal target (not strictly greater)
            if (len == n) {
                continue;
            }

            // At position `len`, we need a character strictly greater than target.charAt(len)
            int targetCharIdx = target.charAt(len) - 'a';
            for (int nextChar = targetCharIdx + 1; nextChar < 26; nextChar++) {
                if (currentCount[nextChar] > 0) {
                    // Form the permutation:
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.substring(0, len));
                    sb.append((char) ('a' + nextChar));
                    currentCount[nextChar]--;

                    // Fill the rest with the smallest available characters
                    for (int c = 0; c < 26; c++) {
                        while (currentCount[c] > 0) {
                            sb.append((char) ('a' + c));
                            currentCount[c]--;
                        }
                    }
                    return sb.toString();
                }
            }
        }

        return "";
    }
}
