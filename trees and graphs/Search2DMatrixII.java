// Leetcode 240

// Write an efficient algorithm that searches for a target value in an
// m x n integer matrix. The matrix has the following properties:
// Integers in each row are sorted in ascending from left to right.
// Integers in each column are sorted in ascending from top to bottom.

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int column = matrix[0].length;
        
        if (matrix == null || row <= 0 || column <= 0)
            return false;
        
        int r = 0;
        int c = column - 1;
        
        while (r < row && c >= 0) {
            if (target == matrix[r][c])
                return true;
            else if (matrix[r][c] > target)
                c--;
            else
                r++;
        }
        return false;
    }
}