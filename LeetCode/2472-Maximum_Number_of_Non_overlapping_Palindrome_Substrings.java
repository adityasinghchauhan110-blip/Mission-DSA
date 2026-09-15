class Solution {
    public int maxPalindromes(String s, int k) {
        int count = 0;
        int lastEnd = -1; // End index of the last selected palindrome
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // Check for palindrome of length k starting at i
            if (i + k <= n && isPalindrome(s, i, i + k - 1)) {
                if (i > lastEnd) {
                    count++;
                    lastEnd = i + k - 1;
                    continue;
                }
            }
            // Check for palindrome of length k + 1 starting at i
            if (i + k + 1 <= n && isPalindrome(s, i, i + k)) {
                if (i > lastEnd) {
                    count++;
                    lastEnd = i + k;
                }
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
