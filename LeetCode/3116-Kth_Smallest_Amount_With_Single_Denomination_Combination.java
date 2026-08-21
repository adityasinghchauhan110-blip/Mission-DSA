class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        
        // Find the minimum coin to set the upper bound for binary search
        long minCoin = coins[0];
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }

        long low = 1;
        long high = minCoin * k;
        long result = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (countAmounts(coins, mid, n) >= k) {
                result = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1;  // Increase the search range
            }
        }

        return result;
    }

    // Counts how many positive integers <= target are divisible by at least one coin in coins
    private long countAmounts(int[] coins, long target, int n) {
        long count = 0;
        int totalSubsets = 1 << n;

        // Iterate through all 2^n - 1 non-empty subsets
        for (int mask = 1; mask < totalSubsets; mask++) {
            long currentLcm = 1;
            int bitCount = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bitCount++;
                    currentLcm = lcm(currentLcm, coins[i]);
                    // Optimization: stop early if LCM exceeds target
                    if (currentLcm > target) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) continue;

            // PIE: Add if odd number of elements, subtract if even
            if (bitCount % 2 != 0) {
                count += target / currentLcm;
            } else {
                count -= target / currentLcm;
            }
        }

        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
