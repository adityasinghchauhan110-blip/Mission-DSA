import java.util.*;

class Solution {
    static class Interval {
        int l, r, weight, id;

        Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }

    static class State {
        long weight;
        List<Integer> indices;

        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }

        static State empty() {
            return new State(0, new ArrayList<>());
        }

        // Returns true if 'this' is strictly better than 'other'
        boolean isBetterThan(State other) {
            if (this.weight != other.weight) {
                return this.weight > other.weight;
            }
            int len = Math.min(this.indices.size(), other.indices.size());
            for (int i = 0; i < len; i++) {
                int a = this.indices.get(i);
                int b = other.indices.get(i);
                if (a != b) {
                    return a < b;
                }
            }
            return this.indices.size() < other.indices.size();
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        Interval[] intervals = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> item = intervalsList.get(i);
            intervals[i] = new Interval(item.get(0), item.get(1), item.get(2), i);
        }

        // Sort by end time
        Arrays.sort(intervals, Comparator.comparingInt((Interval a) -> a.r)
                .thenComparingInt(a -> a.l)
                .thenComparingInt(a -> a.id));

        // dp[i][k]: best state using prefix of first i intervals choosing at most k
        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = State.empty();
            }
        }

        for (int i = 1; i <= n; i++) {
            Interval curr = intervals[i - 1];

            // Binary search for the latest interval j where r_j < curr.l
            int low = 0, high = i - 2, prevIdx = -1;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (intervals[mid].r < curr.l) {
                    prevIdx = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            for (int k = 1; k <= 4; k++) {
                // Option 1: Skip the current interval
                State best = dp[i - 1][k];

                // Option 2: Include the current interval
                State prev = dp[prevIdx + 1][k - 1];
                long newWeight = prev.weight + curr.weight;
                
                // Form the new sorted indices
                List<Integer> newIndices = new ArrayList<>(prev.indices);
                newIndices.add(curr.id);
                Collections.sort(newIndices);

                State candidate = new State(newWeight, newIndices);
                if (candidate.isBetterThan(best)) {
                    best = candidate;
                }

                dp[i][k] = best;
            }
        }

        // Find the best state across all counts <= 4
        State answer = dp[n][0];
        for (int k = 1; k <= 4; k++) {
            if (dp[n][k].isBetterThan(answer)) {
                answer = dp[n][k];
            }
        }

        int[] result = new int[answer.indices.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = answer.indices.get(i);
        }
        return result;
    }
}
