import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int maxEnergy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startX = 0, startY = 0;
        int litterCount = 0;
        int[][] litterId = new int[m][n];
        
        // Identify start position 'S' and assign an ID to each litter 'L'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        // If there's no litter in the classroom, 0 moves are needed.
        if (litterCount == 0) {
            return 0;
        }

        int initialMask = (1 << litterCount) - 1; // All bit flags set to 1
        
        // Queue for BFS storing {row, col, current_energy, mask}
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][][] visited = new boolean[m][n][maxEnergy + 1][1 << litterCount];

        queue.add(new int[]{startX, startY, maxEnergy, initialMask});
        visited[startX][startY][maxEnergy][initialMask] = true;

        int moves = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int energy = curr[2];
                int mask = curr[3];

                // Check if all litter items have been collected
                if (mask == 0) {
                    return moves;
                }

                // If energy drops to 0 and we aren't on a reset cell, we can't move further
                if (energy == 0) {
                    continue;
                }

                // Explore adjacent cells
                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                        char cell = classroom[nr].charAt(nc);
                        
                        // Cannot pass through obstacles
                        if (cell == 'X') {
                            continue;
                        }

                        int nextEnergy = energy - 1;
                        int nextMask = mask;

                        // Refill energy if landing on a reset area
                        if (cell == 'R') {
                            nextEnergy = maxEnergy;
                        } 
                        // Update mask if landing on an uncollected litter item
                        else if (cell == 'L') {
                            int bitIndex = litterId[nr][nc];
                            nextMask &= ~(1 << bitIndex);
                        }

                        if (!visited[nr][nc][nextEnergy][nextMask]) {
                            visited[nr][nc][nextEnergy][nextMask] = true;
                            queue.add(new int[]{nr, nc, nextEnergy, nextMask});
                        }
                    }
                }
            }
            moves++;
        }

        return -1; // Target unreached
    }
}
