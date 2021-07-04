// Leetcode 994

// You are given an m x n grid where each cell can have one of three values:

// 0 representing an empty cell,
// 1 representing a fresh orange, or
// 2 representing a rotten orange.
// Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange
// becomes rotten.

// Return the minimum number of minutes that must elapse until no cell has a fresh orange.
// If this is impossible, return -1.

// Example 1:

// Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
// Output: 4

// Example 2:

// Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
// Output: -1
// Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten,
// because rotting only happens 4-directionally.

// Example 3:

// Input: grid = [[0,2]]
// Output: 0
// Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.

class Orange {
    int timestamp;
    int x, y;
    
    Orange(int timestamp, int x, int y) {
        this.timestamp = timestamp;
        this.x = x;
        this.y = y;
    }
}

class Solution {
    public int orangesRotting(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        
        if (row <= 0 || column <= 0 || grid == null)
            return -1;
        
        Queue<Orange> rottenOranges = new LinkedList<Orange>();
        int timestamp = 0;
            
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == 2)
                    rottenOranges.add(new Orange(timestamp, i, j));
            }
        }
        
        while (!rottenOranges.isEmpty()) {
            Orange orange = rottenOranges.poll();
            
            int x = orange.x;
            int y = orange.y;
            timestamp = orange.timestamp;
            
            // (x, y) -> (x - 1, y), (x + 1, y), (x, y - 1), (x, y + 1)
            
            if (x > 0 && grid[x - 1][y] == 1) {
                rottenOranges.add(new Orange(orange.timestamp + 1, x - 1, y));
                grid[x - 1][y] = 2;
            }
            if (x < row - 1 && grid[x + 1][y] == 1) {
                rottenOranges.add(new Orange(orange.timestamp + 1, x + 1, y));
                grid[x + 1][y] = 2;
            }
            if (y > 0 && grid[x][y - 1] == 1) {
                rottenOranges.add(new Orange(orange.timestamp + 1, x, y - 1));
                grid[x][y - 1] = 2;
            }
            if (y < column - 1 && grid[x][y + 1] == 1) {
                rottenOranges.add(new Orange(orange.timestamp + 1, x, y + 1));
                grid[x][y + 1] = 2;
            }
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) 
                if (grid[i][j] == 1)
                    return -1;
        }
        return timestamp;
    }
}