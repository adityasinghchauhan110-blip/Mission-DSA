import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        
        // Step 1: Record first and last occurrence for each character
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] == -1) {
                first[ch] = i;
            }
            last[ch] = i;
        }
        
        // Step 2: Find all valid intervals [start, end]
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;
            
            int start = first[i];
            int end = last[i];
            boolean isValid = true;
            
            // Expand the boundary to include all occurrences of contained characters
            for (int j = start; j <= end; j++) {
                int ch = s.charAt(j) - 'a';
                if (first[ch] < start) { 
                    // If a character inside has its first occurrence before 'start', 
                    // this start point cannot form a minimal valid substring from here.
                    isValid = false; 
                    break;
                }
                end = Math.max(end, last[ch]);
            }
            
            if (isValid) {
                intervals.add(new int[]{start, end});
            }
        }
        
        // Step 3: Sort intervals by end index (Interval Scheduling Algorithm)
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));
        
        List<String> result = new ArrayList<>();
        int prevEnd = -1;
        
        for (int[] interval : intervals) {
            if (interval[0] > prevEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                prevEnd = interval[1];
            }
        }
        
        return result;
    }
}
