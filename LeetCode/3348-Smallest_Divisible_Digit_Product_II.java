import java.util.*;

class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Prime factorize t into 2, 3, 5, 7
        int[] req = new int[10];
        long tempT = t;
        int[] primes = {2, 3, 5, 7};
        for (int p : primes) {
            while (tempT % p == 0) {
                req[p]++;
                tempT /= p;
            }
        }
        if (tempT > 1) return "-1"; // t has prime factors > 7

        int n = num.length();
        int zeroIdx = num.indexOf('0');

        // Step 2: Precompute cumulative factor counts for prefixes without '0'
        int limit = (zeroIdx == -1) ? n : zeroIdx;
        int[][] prefFactors = new int[limit + 1][10];
        for (int i = 0; i < limit; i++) {
            for (int p = 2; p <= 9; p++) {
                prefFactors[i + 1][p] = prefFactors[i][p];
            }
            addFactor(prefFactors[i + 1], num.charAt(i) - '0', 1);
        }

        // Check if num itself is valid (no '0' and product divisible by t)
        if (zeroIdx == -1 && isSatisfied(prefFactors[n], req)) {
            return num;
        }

        // Step 3: Try to find a solution of length n matching prefix up to position i
        int maxI = (zeroIdx == -1) ? n - 1 : zeroIdx;

        for (int i = maxI; i >= 0; i--) {
            int[] currentPref = prefFactors[i];

            int startDigit;
            if (zeroIdx != -1 && i == zeroIdx) {
                startDigit = 1; // Any non-zero digit here makes it > num
            } else {
                startDigit = (num.charAt(i) - '0') + 1;
            }

            for (int d = startDigit; d <= 9; d++) {
                int[] testFactors = currentPref.clone();
                addFactor(testFactors, d, 1);

                int[] neededReq = getNeeded(testFactors, req);
                String bestSuffix = getMinDigits(neededReq);

                if (bestSuffix.length() <= n - 1 - i) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num.substring(0, i));
                    sb.append(d);
                    int ones = (n - 1 - i) - bestSuffix.length();
                    for (int k = 0; k < ones; k++) sb.append('1');
                    sb.append(bestSuffix);
                    return sb.toString();
                }
            }
        }

        // Step 4: No length n solution found, construct minimal length > n solution
        String bestSuffix = getMinDigits(req);
        int targetLen = Math.max(n + 1, bestSuffix.length());
        StringBuilder sb = new StringBuilder();
        int ones = targetLen - bestSuffix.length();
        for (int k = 0; k < ones; k++) sb.append('1');
        sb.append(bestSuffix);
        return sb.toString();
    }

    private void addFactor(int[] counts, int d, int delta) {
        int temp = d;
        int[] primes = {2, 3, 5, 7};
        for (int p : primes) {
            while (temp % p == 0) {
                counts[p] += delta;
                temp /= p;
            }
        }
    }

    private boolean isSatisfied(int[] current, int[] target) {
        return current[2] >= target[2] && current[3] >= target[3] &&
               current[5] >= target[5] && current[7] >= target[7];
    }

    private int[] getNeeded(int[] current, int[] target) {
        int[] needed = new int[10];
        needed[2] = Math.max(0, target[2] - current[2]);
        needed[3] = Math.max(0, target[3] - current[3]);
        needed[5] = Math.max(0, target[5] - current[5]);
        needed[7] = Math.max(0, target[7] - current[7]);
        return needed;
    }

    private String getMinDigits(int[] needed) {
        int r2 = needed[2], r3 = needed[3], r5 = needed[5], r7 = needed[7];
        String best = null;

        // Try greedily combining prime factors 2 and 3 into digits 8, 9, 6, 4, 3, 2
        for (int c6 = 0; c6 <= 1; c6++) {
            int rem2 = Math.max(0, r2 - c6);
            int rem3 = Math.max(0, r3 - c6);

            int c9 = rem3 / 2;
            int c3 = rem3 % 2;

            int c8 = rem2 / 3;
            int rem2After8 = rem2 % 3;
            int c4 = rem2After8 / 2;
            int c2 = rem2After8 % 2;

            List<Integer> digits = new ArrayList<>();
            for (int k = 0; k < r7; k++) digits.add(7);
            for (int k = 0; k < r5; k++) digits.add(5);
            for (int k = 0; k < c9; k++) digits.add(9);
            for (int k = 0; k < c8; k++) digits.add(8);
            for (int k = 0; k < c6; k++) digits.add(6);
            for (int k = 0; k < c4; k++) digits.add(4);
            for (int k = 0; k < c3; k++) digits.add(3);
            for (int k = 0; k < c2; k++) digits.add(2);

            Collections.sort(digits);
            StringBuilder sb = new StringBuilder();
            for (int d : digits) sb.append(d);
            String candidate = sb.toString();

            if (best == null || candidate.length() < best.length() || 
               (candidate.length() == best.length() && candidate.compareTo(best) < 0)) {
                best = candidate;
            }
        }
        return best;
    }
}
