import java.util.*;

class DemolitionRobot {
    public static int moveObstacle(List<List<Integer>> lot) {
    	int row = lot.size();
    	int column = lot.get(0).size();

    	if (lot == null || row <= 0 || column <= 0)
    		return -1;

        Queue<int[]> lotQueue = new LinkedList<>();
        boolean[][] visited = new boolean[lot.size()][lot.get(0).size()];
        int distance = 0;

        lotQueue.add(new int[] {0, 0, distance});

        while (!lotQueue.isEmpty()) {
        	int[] node = lotQueue.poll();
        	int x = node[0];
        	int y = node[1];
        	distance = node[2];

        	visited[x][y] = true;

        	if (lot.get(x).get(y) == 9)
        		return distance;

        	if (isValid(x - 1, y, row, column, lot, visited))
        		lotQueue.add(new int[]{x - 1, y, distance + 1});

        	if (isValid(x + 1, y, row, column, lot, visited))
        		lotQueue.add(new int[]{x + 1, y, distance + 1});

        	if (isValid(x, y - 1, row, column, lot, visited))
        		lotQueue.add(new int[]{x, y - 1, distance + 1});

        	if (isValid(x, y + 1, row, column, lot, visited))
        		lotQueue.add(new int[]{x, y + 1, distance + 1});
        }
        return distance;
    }

    public static boolean isValid(int x, int y, int row, int column, List<List<Integer>> lot, boolean[][] visited) {
    	if (x < 0 || x >= row || y < 0 || y >= column || visited[x][y] == true || lot.get(x).get(y) == 0)
    		return false;
    	return true;
    }

    public static void main(String[] args) {
        List<List<Integer>> lot = new ArrayList<List<Integer>>();
        lot.add(Arrays.asList(1, 0, 0));
        lot.add(Arrays.asList(1, 9, 0));
        lot.add(Arrays.asList(1, 9, 1));
        System.out.println(moveObstacle(lot));
    }
}