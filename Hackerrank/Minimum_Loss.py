def minimumDistances(a):
    min_dist = float('inf')  
    seen_indices = {}  

    for i, num in enumerate(a):
        if num in seen_indices:
            distance = i - seen_indices[num]
            min_dist = min(min_dist, distance)        
        seen_indices[num] = i
    if min_dist == float('inf'):
        return -1
    else:
        return min_dist
