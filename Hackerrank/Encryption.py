import math

def encryption(s):
    s = s.replace(" ", "")
  
    L = len(s)
    
    rows = math.floor(math.sqrt(L))
    cols = math.ceil(math.sqrt(L))
    if rows * cols < L:
        rows = cols 
    grid = []
    for i in range(0, L, cols):
        grid.append(s[i : i + cols])
        
    encrypted_text = ""
    for j in range(cols):  
        for i in range(len(grid)):  
            if j < len(grid[i]):  
                encrypted_text += grid[i][j]
        encrypted_text += " " 
        
    return encrypted_text.strip()
