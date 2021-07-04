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

class GuardsAndBank {
	public static char[][] findDistance(char[][] matrix) {
		int row = matrix.length;
		int column = matrix[0].length;
		int distance = 0;
		char[][] output = new char[row][column];

		if (matrix == null || row <= 0 || column <= 0)
			return;

		Queue<Point> guardQueue = new LinkedList<Point>();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				output[i][j] = -1;
				if (matrix[i][j] == 'G') {
					guardQueue.add(new Point(i, j, distance));
					output[i][j] = 0;
				}
			}
		}

		while (!guardQueue.isEmpty()) {
			Point point = guardQueue.poll();
			int x = point.x;
			int y = point.y;
			distance = point.distance;

			for (int i = 0; i < 4; i++) {
				
			}
		}
	}

	public static void main(String args[]) {
	    char matrix[][] = {
	    	{ 'O', 'O', 'O', 'O', 'G' },
	        { 'O', 'W', 'W', 'O', 'O' },
            { 'O', 'O', 'O', 'W', 'O' },
            { 'G', 'W', 'W', 'W', 'O' },
            { 'O', 'O', 'O', 'O', 'G' }
        };
	                         
	    char[][] output = findDistance(matrix);

	    for (int i = 0; i < output.length; i++) {
	    	for (int j = 0; j < output[0].length; j++)
	    		System.out.print(output[i][j] + " ");
	    	System.out.println();
	    }
	}
}