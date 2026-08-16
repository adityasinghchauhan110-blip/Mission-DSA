class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        for (int stone : stones) {
            cnt[stone % 3]++;
        }

        // Case 1: Even number of 0-remainder stones
        if (cnt[0] % 2 == 0) {
            return cnt[1] > 0 && cnt[2] > 0;
        }

        // Case 2: Odd number of 0-remainder stones
        return Math.abs(cnt[1] - cnt[2]) > 2;
    }
}
