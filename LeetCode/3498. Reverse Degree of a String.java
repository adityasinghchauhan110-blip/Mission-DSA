class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // Reversed alphabet value: 'a' -> 26, 'b' -> 25, ..., 'z' -> 1
            int reversedAlphabetVal = 'z' - s.charAt(i) + 1;
            
            // 1-based string index from left to right
            int stringIndex = i + 1;
            
            totalDegree += reversedAlphabetVal * stringIndex;
        }

        return totalDegree;
    }
}
