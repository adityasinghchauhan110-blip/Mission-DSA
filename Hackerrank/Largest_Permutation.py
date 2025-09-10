def largestPermutation(k, arr):
    n = len(arr)
    index_map = {value: idx for idx, value in enumerate(arr)}
    for i in range(n):
        if k == 0:
            break
        correct_value = n - i
        if arr[i] == correct_value:
            continue
        to_swap_idx = index_map[correct_value]
        arr[i], arr[to_swap_idx] = arr[to_swap_idx], arr[i]
        index_map[arr[to_swap_idx]] = to_swap_idx
        index_map[arr[i]] = 
        k -= 1
    return arr
arr = [4, 2, 3, 5, 1]
k = 2
print(largestPermutation(k, arr))
