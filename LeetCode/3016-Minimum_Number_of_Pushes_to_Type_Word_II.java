import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        // Step 1: Count frequency of each letter
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        // Step 2: Sort frequencies in ascending order
        Arrays.sort(freq);

        int totalPushes = 0;
        int lettersAssigned = 0;

        // Step 3: Iterate from highest frequency to lowest
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) {
                break; // No more characters left
            }

            // Calculate cost (1 push for first 8 letters, 2 for next 8, etc.)
            int cost = (lettersAssigned / 8) + 1;
            totalPushes += freq[i] * cost;
            lettersAssigned++;
        }

        return totalPushes;
    }
}
