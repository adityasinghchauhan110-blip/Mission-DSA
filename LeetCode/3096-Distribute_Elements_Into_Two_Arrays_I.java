class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        
        // Lists to dynamically grow arr1 and arr2
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        
        // Step 1 & 2: Seed the first two elements
        list1.add(nums[0]);
        list2.add(nums[1]);
        
        // Step 3: Distribute remaining elements
        for (int i = 2; i < n; i++) {
            int last1 = list1.get(list1.size() - 1);
            int last2 = list2.get(list2.size() - 1);
            
            if (last1 > last2) {
                list1.add(nums[i]);
            } else {
                list2.add(nums[i]);
            }
        }
        
        // Step 4: Combine list1 and list2 into the result array
        int[] result = new int[n];
        int index = 0;
        
        for (int num : list1) {
            result[index++] = num;
        }
        for (int num : list2) {
            result[index++] = num;
        }
        
        return result;
    }
}
