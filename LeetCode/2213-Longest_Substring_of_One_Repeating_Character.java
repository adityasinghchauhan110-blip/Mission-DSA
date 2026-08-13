class Solution {
    private class Node {
        int maxLen;
        int prefLen;
        char prefChar;
        int suffLen;
        char suffChar;
        int len;

        Node(char c) {
            this.maxLen = 1;
            this.prefLen = 1;
            this.prefChar = c;
            this.suffLen = 1;
            this.suffChar = c;
            this.len = 1;
        }

        Node() {}
    }

    private Node[] tree;

    private Node merge(Node left, Node right) {
        Node res = new Node();
        res.len = left.len + right.len;

        // Default prefix
        res.prefChar = left.prefChar;
        res.prefLen = left.prefLen;
        if (left.prefLen == left.len && left.prefChar == right.prefChar) {
            res.prefLen += right.prefLen;
        }

        // Default suffix
        res.suffChar = right.suffChar;
        res.suffLen = right.suffLen;
        if (right.suffLen == right.len && right.suffChar == left.suffChar) {
            res.suffLen += left.suffLen;
        }

        // Max repeating length
        res.maxLen = Math.max(left.maxLen, right.maxLen);
        if (left.suffChar == right.prefChar) {
            res.maxLen = Math.max(res.maxLen, left.suffLen + right.prefLen);
        }

        return res;
    }

    private void build(char[] chars, int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(chars, 2 * node, start, mid);
        build(chars, 2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            tree[node] = new Node(c);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, c);
        } else {
            update(2 * node + 1, mid + 1, end, idx, c);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        tree = new Node[4 * n];

        char[] chars = s.toCharArray();
        build(chars, 1, 0, n - 1);

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            update(1, 0, n - 1, idx, c);
            ans[i] = tree[1].maxLen;
        }

        return ans;
    }
}
