class Solution {
    static class Node {
        int[] remain = new int[5];
        int prod = 1;
    }

    private int k;
    private Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        int n = nums.length;
        int m = queries.length;

        int[] modNums = new int[n];
        for (int i = 0; i < n; i++) {
            modNums[i] = (int) (nums[i] % k);
        }

        tree = new Node[4 * n];
        build(0, 0, n - 1, modNums);

        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int idx = queries[i][0];
            int val = (int) (queries[i][1] % k);
            int start = queries[i][2];
            int x = queries[i][3];

            update(0, 0, n - 1, idx, val);

            Node res = query(0, 0, n - 1, start, n - 1);
            ans[i] = res.remain[x];
        }

        return ans;
    }

    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node parent = new Node();
        parent.prod = (left.prod * right.prod) % k;

        for (int i = 0; i < k; i++) {
            parent.remain[i] = left.remain[i];
        }

        for (int i = 0; i < k; i++) {
            int targetMod = (i * left.prod) % k;
            parent.remain[targetMod] += right.remain[i];
        }

        return parent;
    }

    private void build(int node, int l, int r, int[] nums) {
        tree[node] = new Node();
        if (l == r) {
            tree[node].remain[nums[l]] = 1;
            tree[node].prod = nums[l];
            return;
        }
        int mid = l + (r - l) / 2;
        build(2 * node + 1, l, mid, nums);
        build(2 * node + 2, mid + 1, r, nums);
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            tree[node] = new Node();
            tree[node].remain[val] = 1;
            tree[node].prod = val;
            return;
        }
        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(2 * node + 1, l, mid, idx, val);
        } else {
            update(2 * node + 2, mid + 1, r, idx, val);
        }
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }
        int mid = l + (r - l) / 2;
        if (qr <= mid) {
            return query(2 * node + 1, l, mid, ql, qr);
        }
        if (ql > mid) {
            return query(2 * node + 2, mid + 1, r, ql, qr);
        }

        Node leftRes = query(2 * node + 1, l, mid, ql, qr);
        Node rightRes = query(2 * node + 2, mid + 1, r, ql, qr);
        return merge(leftRes, rightRes);
    }
}
