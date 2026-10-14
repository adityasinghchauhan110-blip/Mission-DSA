class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the maximum possible sum with 'num' digits is less than 'sum', it's impossible
        if ((long) num * 9 < sum) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        // Greedily use as many '9's as possible
        int k = sum / 9;
        int rem = sum % 9;
        
        for (int i = 0; i < k; i++) {
            sb.append('9');
        }
        
        // If there is a remainder, append it as the next digit
        if (rem > 0) {
            sb.append(rem);
        }
        
        // Fill the remaining positions with '0's to match the required length 'num'
        while (sb.length() < num) {
            sb.append('0');
        }
        
        return sb.toString();
    }
}
