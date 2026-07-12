import java.util.*;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        // Step 1: Copy and sort the array to determine ranks
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        // Step 2: Map each unique value to its rank
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;

        for (int num : sorted) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank);
                rank++;
            }
        }

        // Step 3: Build result array using the rank map
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] arr1 = {40, 10, 20, 30};
        System.out.println(Arrays.toString(sol.arrayRankTransform(arr1))); // [4, 1, 2, 3]

        int[] arr2 = {100, 100, 100};
        System.out.println(Arrays.toString(sol.arrayRankTransform(arr2))); // [1, 1, 1]

        int[] arr3 = {37, 12, 28, 9, 100, 56, 80, 5, 12};
        System.out.println(Arrays.toString(sol.arrayRankTransform(arr3))); // [5, 3, 4, 2, 8, 6, 7, 1, 3]
    }
}
