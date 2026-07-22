import java.util.ArrayList;
import java.util.List;

class Solution {
    private static class Group {
        int start;
        int length;

        Group(int start, int length) {
            this.start = start;
            this.length = length;
        }
    }

    private static class SparseTable {
        private final int[][] st;

        public SparseTable(int[] nums) {
            int n = nums.length;
            int maxLog = Integer.SIZE - Integer.numberOfLeadingZeros(n);
            st = new int[maxLog][n];

            for (int j = 0; j < n; ++j) {
                st[0][j] = nums[j];
            }

            for (int i = 1; i < maxLog; ++i) {
                for (int j = 0; j + (1 << i) <= n; ++j) {
                    st[i][j] = Math.max(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);
                }
            }
        }

        public int query(int l, int r) {
            if (l > r) return 0;
            int i = Integer.SIZE - Integer.numberOfLeadingZeros(r - l + 1) - 1;
            return Math.max(st[i][l], st[i][r - (1 << i) + 1]);
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int ones = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') ones++;
        }

        var p = getZeroGroups(s);
        List<Group> zeroGroups = p.zeroGroups;
        int[] zeroGroupIndex = p.zeroGroupIndex;

        int[] zeroMergeLengths = getZeroMergeLengths(zeroGroups);
        SparseTable st = zeroMergeLengths.length > 0 ? new SparseTable(zeroMergeLengths) : null;

        List<Integer> ans = new ArrayList<>(queries.length);

        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];

            int left = zeroGroupIndex[l] == -1 ? -1 : (zeroGroups.get(zeroGroupIndex[l]).start + zeroGroups.get(zeroGroupIndex[l]).length - l);
            int right = zeroGroupIndex[r] == -1 ? -1 : (r - zeroGroups.get(zeroGroupIndex[r]).start + 1);

            int startAdjacent = zeroGroupIndex[l] + 1;
            int endAdjacent = (s.charAt(r) == '1' ? zeroGroupIndex[r] : zeroGroupIndex[r] - 1) - 1;

            int activeSections = ones;

            if (s.charAt(l) == '0' && s.charAt(r) == '0' && zeroGroupIndex[l] + 1 == zeroGroupIndex[r]) {
                activeSections = Math.max(activeSections, ones + left + right);
            } else if (st != null && startAdjacent <= endAdjacent) {
                activeSections = Math.max(activeSections, ones + st.query(startAdjacent, endAdjacent));
            }

            if (s.charAt(l) == '0' && zeroGroupIndex[l] + 1 <= (s.charAt(r) == '1' ? zeroGroupIndex[r] : zeroGroupIndex[r] - 1)) {
                activeSections = Math.max(activeSections, ones + left + zeroGroups.get(zeroGroupIndex[l] + 1).length);
            }

            if (s.charAt(r) == '0' && zeroGroupIndex[l] < zeroGroupIndex[r] - 1) {
                activeSections = Math.max(activeSections, ones + right + zeroGroups.get(zeroGroupIndex[r] - 1).length);
            }

            ans.add(activeSections);
        }

        return ans;
    }

    private static class Pair {
        List<Group> zeroGroups;
        int[] zeroGroupIndex;

        Pair(List<Group> zeroGroups, int[] zeroGroupIndex) {
            this.zeroGroups = zeroGroups;
            this.zeroGroupIndex = zeroGroupIndex;
        }
    }

    private Pair getZeroGroups(String s) {
        List<Group> zeroGroups = new ArrayList<>();
        int[] zeroGroupIndex = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    zeroGroups.get(zeroGroups.size() - 1).length++;
                } else {
                    zeroGroups.add(new Group(i, 1));
                }
                zeroGroupIndex[i] = zeroGroups.size() - 1;
            } else {
                zeroGroupIndex[i] = zeroGroups.isEmpty() ? -1 : zeroGroups.size() - 1;
            }
        }

        return new Pair(zeroGroups, zeroGroupIndex);
    }

    private int[] getZeroMergeLengths(List<Group> zeroGroups) {
        if (zeroGroups.size() < 2) return new int[0];
        int[] zeroMergeLengths = new int[zeroGroups.size() - 1];
        for (int i = 0; i < zeroGroups.size() - 1; i++) {
            zeroMergeLengths[i] = zeroGroups.get(i).length + zeroGroups.get(i + 1).length;
        }
        return zeroMergeLengths;
    }
}
