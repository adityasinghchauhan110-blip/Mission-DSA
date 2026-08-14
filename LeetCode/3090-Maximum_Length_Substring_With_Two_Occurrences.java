class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            // Add current character to window frequency
            count[s.charAt(right) - 'a']++;

            // Shrink window from left if current character exceeds 2 occurrences
            while (count[s.charAt(right) - 'a'] > 2) {
                count[s.charAt(left) - 'a']--;
                left++;
            }

            // Update maximum valid substring length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
