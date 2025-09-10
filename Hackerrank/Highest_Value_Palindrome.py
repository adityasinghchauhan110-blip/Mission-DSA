def highestValuePalindrome(s, n, k):
    s_list = list(s)
    changed = [False] * n
    l, r = 0, n - 1
    while l < r:
        if s_list[l] != s_list[r]:
            if k == 0:
                return "-1"
            if s_list[l] > s_list[r]:
                s_list[r] = s_list[l]
            else:
                s_list[l] = s_list[r]
            k -= 1
            changed[l] = changed[r] = True
        l += 1
        r -= 1

    if k < 0:
        return "-1"

    l, r = 0, n - 1
    while l <= r and k > 0:
        if l == r:  
            if s_list[l] != '9':
                s_list[l] = '9'
                k -= 1
        elif s_list[l] != '9':
            if changed[l] or changed[r]:  
                s_list[l] = s_list[r] = '9'
                k -= 1
            elif k >= 2:  
                s_list[l] = s_list[r] = '9'
                k -= 2
        l += 1
        r -= 1

    return "".join(s_list)
