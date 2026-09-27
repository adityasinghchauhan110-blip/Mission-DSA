import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        // 1. Pair each '(' with its matching ')'
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int openIndex = stack.pop();
                pair[openIndex] = i;
                pair[i] = openIndex;
            }
        }

        // 2. Traverse and build the string
        StringBuilder sb = new StringBuilder();
        int curr = 0;
        int direction = 1; // 1 = forward, -1 = backward

        while (curr >= 0 && curr < n) {
            char c = s.charAt(curr);
            if (c == '(' || c == ')') {
                curr = pair[curr];       // Jump to matching bracket
                direction = -direction;  // Flip direction
            } else {
                sb.append(c);
            }
            curr += direction;
        }

        return sb.toString();
    }
}
