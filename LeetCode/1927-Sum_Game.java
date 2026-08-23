class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumDiff = 0;
        int questionDiff = 0;

        // Process the left half
        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                questionDiff++;
            } else {
                sumDiff += c - '0';
            }
        }

        // Process the right half
        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                questionDiff--;
            } else {
                sumDiff -= c - '0';
            }
        }

        // If the difference in question marks is even, check if Bob can balance it out.
        // sumDiff + (questionDiff / 2) * 9 == 0 means Bob wins (returns false).
        // If questionDiff is odd, Bob cannot balance it out, so Alice wins (returns true).
        return sumDiff * 2 != -questionDiff * 9;
    }
}
