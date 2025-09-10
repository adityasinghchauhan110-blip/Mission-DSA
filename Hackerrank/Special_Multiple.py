from collections import deque

def specialMultiple(n):
    q = deque()
    q.append("9")
    visited = set()
    visited.add("9")

    while q:
        current_num_str = q.popleft()
        current_num = int(current_num_str)

        if current_num % n == 0:
            return current_num_str

        # Generate next candidates
        next_num_str_0 = current_num_str + "0"
        if next_num_str_0 not in visited:
            q.append(next_num_str_0)
            visited.add(next_num_str_0)

        next_num_str_9 = current_num_str + "9"
        if next_num_str_9 not in visited:
            q.append(next_num_str_9)
            visited.add(next_num_str_9)
