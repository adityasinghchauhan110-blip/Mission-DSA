class Solution {
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Path length is m + n - 1. Must be even to be balanced.
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        // memo[r][c][balance]
        int maxBalance = (m + n) / 2;
        memo = new Boolean[m][n][maxBalance + 1];

        return dfs(grid, 0, 0, 0, m, n, maxBalance);
    }

    private boolean dfs(char[][] grid, int r, int c, int balance, int m, int n, int maxBalance) {
        // Update balance for current cell
        balance += (grid[r][c] == '(') ? 1 : -1;

        // If balance drops negative or exceeds what can be closed in remaining steps
        if (balance < 0 || balance > maxBalance) {
            return false;
        }

        // Reached destination
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }

        boolean found = false;

        // Move Down
        if (r + 1 < m) {
            found = dfs(grid, r + 1, c, balance, m, n, maxBalance);
        }

        // Move Right
        if (!found && c + 1 < n) {
            found = dfs(grid, r, c + 1, balance, m, n, maxBalance);
        }

        return memo[r][c][balance] = found;
    }
}
