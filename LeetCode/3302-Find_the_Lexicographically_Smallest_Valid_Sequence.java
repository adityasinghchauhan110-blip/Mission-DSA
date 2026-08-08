class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // last[j] stores the maximum starting index in word1 
        // to match word2[j ... m-1] exactly (0 mismatches).
        int[] last = new int[m + 1];
        last[m] = n;

        int ptr = n - 1;
        for (int j = m - 1; j >= 0; j--) {
            while (ptr >= 0 && word1.charAt(ptr) != word2.charAt(j)) {
                ptr--;
            }
            last[j] = ptr;
            if (ptr >= 0) {
                ptr--;
            }
        }

        int[] ans = new int[m];
        boolean changed = false;
        int i = 0;

        for (int j = 0; j < m; j++) {
            boolean matched = false;

            while (i < n) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    if (changed) {
                        // If mismatch already used, rest must match exactly
                        if (last[j + 1] > i) {
                            ans[j] = i++;
                            matched = true;
                            break;
                        }
                    } else {
                        // Matching character without using mismatch
                        ans[j] = i++;
                        matched = true;
                        break;
                    }
                } else {
                    // Try using the allowed 1 mismatch here
                    if (!changed && last[j + 1] > i) {
                        changed = true;
                        ans[j] = i++;
                        matched = true;
                        break;
                    }
                }
                i++;
            }

            // If we couldn't match word2[j], no valid sequence exists
            if (!matched) {
                return new int[0];
            }
        }

        return ans;
    }
}
