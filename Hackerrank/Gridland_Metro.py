def gridlandMetro(n, m, k, track):
    tracks_by_row = {}
    for r, c1, c2 in track:
        if r not in tracks_by_row:
            tracks_by_row[r] = []
        tracks_by_row[r].append([c1, c2])

    occupied_cells = 0
    for row in tracks_by_row.values():
        row.sort() 
        
        merged_segments = []
        if row:
            current_start, current_end = row[0]
            for i in range(1, len(row)):
                next_start, next_end = row[i]
                if next_start <= current_end + 1: 
                    current_end = max(current_end, next_end)
                else:
                    merged_segments.append([current_start, current_end])
                    current_start, current_end = next_start, next_end
            merged_segments.append([current_start, current_end]) 

        for s, e in merged_segments:
            occupied_cells += (e - s + 1)

    return (n * m) - occupied_cells
