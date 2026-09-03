class Solution:
    def uniformArray(self, nums1: list[int]) -> bool:
        # Check if all elements already have the same parity
        first_parity = nums1[0] % 2
        if all(x % 2 == first_parity for x in nums1):
            return True
        
        # If mixed parity, it's only possible if the minimum element is odd
        return min(nums1) % 2 != 0
