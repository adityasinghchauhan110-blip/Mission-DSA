import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;

    class SegmentTree {
        int n;
        long[] sum;      // stores sum of elements in range
        long[] sumSq;    // stores sum of squares of elements in range
        long[] lazy;     // lazy propagation array for range increments

        public SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            sumSq = new long[4 * n];
            lazy = new long[4 * n];
        }

        private void apply(int node, int l, int r, long val) {
            // (x + val)^2 = x^2 + 2*val*x + val^2 * (length)
            int len = r - l + 1;
            sumSq[node] = (sumSq[node] + 2 * val * sum[node] + val * val % MOD * len) % MOD;
            sum[node] = (sum[node] + val * len) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        private void pushDown(int node, int l, int r) {
            if (lazy[node] != 0) {
                int mid = l + (r - l) / 2;
                apply(2 * node, l, mid, lazy[node]);
                apply(2 * node + 1, mid + 1, r, lazy[node]);
                lazy[node] = 0;
            }
        }

        public void update(int node, int l, int r, int ql, int qr, long val) {
            if (ql > r || qr < l) return;
            if (ql <= l && r <= qr) {
                apply(node, l, r, val);
                return;
            }
            pushDown(node, l, r);
            int mid = l + (r - l) / 2;
            update(2 * node, l, mid, ql, qr, val);
            update(2 * node + 1, mid + 1, r, ql, qr, val);
            sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
            sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        }

        public long querySumSq() {
            return sumSq[1];
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        Map<Integer, Integer> lastSeen = new HashMap<>();
        long totalSumSq = 0;

        for (int r = 0; r < n; r++) {
            int l = lastSeen.getOrDefault(nums[r], -1) + 1;
            // Update range [l, r] by adding 1 to distinct counts
            tree.update(1, 0, n - 1, l, r, 1);
            lastSeen.put(nums[r], r);
            
            // Add the sum of squares of all ending at index r
            totalSumSq = (totalSumSq + tree.querySumSq()) % MOD;
        }

        return (int) totalSumSq;
    }
}
