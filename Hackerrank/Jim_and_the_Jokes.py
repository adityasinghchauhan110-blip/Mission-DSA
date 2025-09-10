def base_to_decimal(day_str, base):
    try:
        
        return int(day_str, base)
    except ValueError:
      
        return None

def jim_and_jokes(dates):
    value_count = {}
    for month, day in dates:
       
        day_str = str(day)
        
        if all(int(d) < month for d in day_str):
            val = base_to_decimal(day_str, month)
            if val is not None:
                if val in value_count:
                    value_count[val] += 1
                else:
                    value_count[val] = 1
    
    pairs = sum(n * (n-1) // 2 for n in value_count.values())
    return pairs


dates = [(10, 12), (8, 14), (10, 14), (8, 12)]
print(jim_and_jokes(dates)) 
