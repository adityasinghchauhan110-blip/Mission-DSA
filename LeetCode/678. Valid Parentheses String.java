class Solution {
    public boolean checkValidString(String s) {
        int cmin = 0; // Minimum possible open '(' count
        int cmax = 0; // Maximum possible open '(' count

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                cmin++;
                cmax++;
            } else if (ch == ')') {
                cmin--;
                cmax--;
            } else if (ch == '*') {
                cmin--; // '*' acts as ')'
                cmax++; // '*' acts as '('
            }

            // Too many ')' even if all '*' were treated as '('
            if (cmax < 0) {
                return false;
            }

            // cmin cannot be negative; '*' can just act as empty string ""
            cmin = Math.max(cmin, 0);
        }

        // Valid if 0 open parentheses falls within the reachable range
        return cmin == 0;
    }
}
