import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) return res;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(s);
        visited.add(s);
        boolean found = false;

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            if (isValid(curr)) {
                res.add(curr);
                found = true;
            }

            // Once we find a valid string on this level, do not generate the next level
            if (found) continue;

            for (int i = 0; i < curr.length(); i++) {
                char ch = curr.charAt(i);
                if (ch != '(' && ch != ')') continue;

                // Create substring removing character at index i
                String next = curr.substring(0, i) + curr.substring(i + 1);
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }

        return res;
    }

    private boolean isValid(String str) {
        int count = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '(') count++;
            else if (ch == ')') {
                count--;
                if (count < 0) return false;
            }
        }
        return count == 0;
    }
}
