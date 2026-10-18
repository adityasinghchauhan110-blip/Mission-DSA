import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Precompute combinations nCr modulo 10^9 + 7 using Pascal's Triangle
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>(n);
        for (int num : nums) {
            list.add(num);
        }

        // Subtract 1 because the original ordering is excluded
        long totalWays = dfs(list);
        return (int) ((totalWays - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> list) {
        if (list.size() <= 2) {
            return 1;
        }

        int root = list.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {
            int val = list.get(i);
            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        int l = left.size();
        int r = right.size();

        // ways = comb[l + r][l] * leftWays * rightWays % MOD
        long ways = (comb[l + r][l] * leftWays) % MOD;
        ways = (ways * rightWays) % MOD;

        return ways;
    }
}
