class Solution {
    public int maxProduct(int n) {
        // Handle negative numbers if any
        n = Math.abs(n);
        
        int max1 = 0;
        int max2 = 0;
        
        while (n > 0) {
            int digit = n % 10;
            
            // Update the top two maximum digits found so far
            if (digit > max1) {
                max2 = max1;
                max1 = digit;
            } else if (digit > max2) {
                max2 = digit;
            }
            
            n /= 10;
        }
        
        return max1 * max2;
    }
}
