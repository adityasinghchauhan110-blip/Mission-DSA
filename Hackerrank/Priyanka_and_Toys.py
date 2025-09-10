def toys(w):
    
    if not w:
        return 0
    w.sort()

    containers = 0
    i = 0
    n = len(w)

    while i < n:
        containers += 1
        current_container_min = w[i]
        while i < n and w[i] <= current_container_min + 4:
            i += 1
    return containers

if _name_ == '_main_':
    n = int(input().strip())
    w = list(map(int, input().strip().split(' ')))

    result = toys(w)
    print(result)
