def nonDivisibleSubset(k, s):
    remainder_counts = [0] * k

    for number in s:
        remainder_counts[number % k] += 1

    max_subset_size = 0
    if remainder_counts[0] > 0:
        max_subset_size += 1

    for r in range(1, (k // 2) + 1):
        if r != k - r:
            max_subset_size += max(remainder_counts[r], remainder_counts[k - r])
        else:  # r == k - r, only for even k and r = k/2
            if remainder_counts[r] > 0:
                max_subset_size += 1

    return max_subset_size
