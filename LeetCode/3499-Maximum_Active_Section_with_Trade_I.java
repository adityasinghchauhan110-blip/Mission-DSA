class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int ones = 0;
        int maxGain = 0;

        int prevZero = -1;
        int i = 0;

        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }

            int len = j - i;

            if (s.charAt(i) == '1') {
                ones += len;
            } else {
                if (prevZero != -1) {
                    maxGain = Math.max(maxGain, prevZero + len);
                }
                prevZero = len;
            }

            i = j;
        }

        return ones + maxGain;
    }
}
