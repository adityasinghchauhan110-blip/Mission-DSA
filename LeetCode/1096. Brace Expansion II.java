import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Character> ops = new Stack<>();
        Stack<Set<String>> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '{') {
                // If previous char was '}' or an alphabet letter, insert an implicit multiplication '*'
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)))) {
                    while (!ops.isEmpty() && ops.peek() == '*') {
                        evaluate(ops, stack);
                    }
                    ops.push('*');
                }
                ops.push('{');
            } else if (c == ',') {
                // Evaluate all operations inside the current brace before processing union
                while (!ops.isEmpty() && ops.peek() != '{') {
                    evaluate(ops, stack);
                }
                ops.push(',');
            } else if (c == '}') {
                // Evaluate until matching '{'
                while (!ops.isEmpty() && ops.peek() != '{') {
                    evaluate(ops, stack);
                }
                ops.pop(); // Remove '{'
            } else {
                // Character is a lowercase letter
                // Check if implicit concatenation/multiplication is needed
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)))) {
                    while (!ops.isEmpty() && ops.peek() == '*') {
                        evaluate(ops, stack);
                    }
                    ops.push('*');
                }

                Set<String> set = new HashSet<>();
                set.add(String.valueOf(c));
                stack.push(set);
            }
        }

        // Evaluate any remaining operations in the stack
        while (!ops.isEmpty()) {
            evaluate(ops, stack);
        }

        // Convert the result set to a sorted list
        List<String> result = new ArrayList<>(stack.pop());
        Collections.sort(result);
        return result;
    }

    private void evaluate(Stack<Character> ops, Stack<Set<String>> stack) {
        char op = ops.pop();
        Set<String> set2 = stack.pop();
        Set<String> set1 = stack.pop();

        Set<String> res = new HashSet<>();

        if (op == '*') {
            // Concatenation (Cartesian product)
            for (String s1 : set1) {
                for (String s2 : set2) {
                    res.add(s1 + s2);
                }
            }
        } else if (op == ',') {
            // Union
            res.addAll(set1);
            res.addAll(set2);
        }

        stack.push(res);
    }
}
