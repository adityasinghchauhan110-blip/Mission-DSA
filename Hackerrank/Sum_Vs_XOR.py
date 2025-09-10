def sumXor(n):
    
    if n == 0:
        return 1  
    binary_n = bin(n)[2:]

    count_of_zeros = binary_n.count('0')

    return 2 ** count_of_zeros
