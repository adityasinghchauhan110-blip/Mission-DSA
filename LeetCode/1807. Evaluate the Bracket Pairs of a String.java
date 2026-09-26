import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Step 1: Build a HashMap for O(1) key-value lookup
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder currentKey = new StringBuilder();
        boolean insideBracket = false;

        // Step 2: Traverse the string
        for (char c : s.toCharArray()) {
            if (c == '(') {
                insideBracket = true;
            } else if (c == ')') {
                insideBracket = false;
                String key = currentKey.toString();
                // Replace key with value if present, else "?"
                result.append(map.getOrDefault(key, "?"));
                currentKey.setLength(0); // Reset for the next key
            } else {
                if (insideBracket) {
                    currentKey.append(c);
                } else {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}
