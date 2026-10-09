class Solution {
    public int minInsertions(String s) {
        int res = 0;      // Stores total insertions required
        int needed = 0;   // Stores the number of ')' needed

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // If needed is odd, we have an unclosed single ')' for a previous '('
                // Insert one ')' immediately to complete the "))" pair
                if (needed % 2 != 0) {
                    res++;
                    needed--;
                }
                needed += 2;
            } else { // c == ')'
                needed--;
                // Extra ')' without a matching '('
                if (needed == -1) {
                    res++;      // Insert one '('
                    needed = 1;  // That '(' needs 2 ')', and we just consumed 1 ')'
                }
            }
        }

        return res + needed;
    }
}
