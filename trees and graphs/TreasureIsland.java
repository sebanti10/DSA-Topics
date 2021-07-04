// You have a map that marks the location of a treasure island.
// Some of the map area has jagged rocks and dangerous reefs.
// Other areas are safe to sail in. There are other explorers trying to find the treasure.
// So you must figure out a shortest route to the treasure island.

// Assume the map area is a two dimensional grid, represented by a matrix of characters.
// You must start from the top-left corner of the map and can move one block up, down,
// left or right at a time. The treasure island is marked as X in a block of the matrix.
// X will not be at the top-left corner. Any block with dangerous rocks or reefs will be
// marked as D. You must not enter dangerous blocks. You cannot leave the map area.
// Other areas O are safe to sail in. The top-left corner is always safe.
// Output the minimum number of steps to get to the treasure.

// Example:

// Input:
// [['O', 'O', 'O', 'O'],
//  ['D', 'O', 'D', 'O'],
//  ['O', 'O', 'O', 'O'],
//  ['X', 'D', 'D', 'O']]

// Output: 5
// Explanation: Route is (0, 0), (0, 1), (1, 1), (2, 1), (2, 0), (3, 0)
// The minimum route takes 5 steps.

import java.util.*;

class Point {
	int x;
	int y;
	int distance;

	Point(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}
}

class TreasureIsland {
	public static int minSteps(char[][] grid) {
		int row = grid.length;
		int column = grid[0].length;

		if (grid == null || row <= 0 || column <= 0)
			return 0;

		Queue<Point> mapQueue = new LinkedList<Point>();
		// boolean[][] visited = new boolean[row][column];
		int distance = 0;
		mapQueue.add(new Point(0, 0, distance));
		grid[0][0] = 'D';

		while (!mapQueue.isEmpty()) {
			Point point = mapQueue.poll();
			int x = point.x;
			int y = point.y;
			distance = point.distance;

			// visited[x][y] = true;

			if (grid[x][y] == 'X')
				return distance;

			if (isSafe(x - 1, y, row, column, grid)) {
				mapQueue.add(new Point(x - 1, y, distance + 1));
				grid[x][y] = 'D';
			}

			if (isSafe(x + 1, y, row, column, grid)) {
				mapQueue.add(new Point(x + 1, y, distance + 1));
				grid[x][y] = 'D';
			}

			if (isSafe(x, y - 1, row, column, grid)) {
				mapQueue.add(new Point(x, y - 1, distance + 1));
				grid[x][y] = 'D';
			}

			if (isSafe(x, y + 1, row, column, grid)) {
				mapQueue.add(new Point(x, y + 1, distance + 1));
				grid[x][y] = 'D';
			}
		}
		return distance;
	}

	public static boolean isSafe(int x, int y, int row, int column, char[][] grid) {
		if (x < 0 || x >= row || y < 0 || y >= column || grid[x][y] == 'D') {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		char[][] grid = {
			{'O', 'O', 'O', 'O'},
			{'D', 'O', 'D', 'O'},
			{'O', 'O', 'O', 'O'},
			{'X', 'D', 'D', 'O'}
		};
		System.out.println(minSteps(grid));
	}
}