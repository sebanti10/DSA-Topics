import java.util.*;

class ConnectedComponents {
	public static int countComponents(int n, int[][] edges) {
		HashMap<Integer, List<Integer>> adjacencyList = new HashMap<>();

		for (int i = 0; i < n; i++)
			adjacencyList.put(i, new LinkedList<Integer>());

		for (int[] edge: edges) {
			adjacencyList.get(edge[0]).add(edge[1]);
			adjacencyList.get(edge[1]).add(edge[0]);
		}

		boolean[] visited = new boolean[n];
		int noOfComponents = 0;

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				noOfComponents++;
				dfs(i, adjacencyList, visited);
			}
		}
		return noOfComponents;
	}

	public static void dfs(int vertex, HashMap<Integer, List<Integer>> adjacencyList, boolean[] visited) {
		visited[vertex] = true;

		for (Integer adjacentVertex: adjacencyList.get(vertex)) {
			if (!visited[adjacentVertex])
				dfs(adjacentVertex, adjacencyList, visited);
		}
	}

	public static void main(String[] args) {
		System.out.println(countComponents(5, new int[][]{{0, 1}, {1, 2}, {3, 4}}));
	}

	// 0 [1 -> null]
	// 1 [0 -> 2 -> null]
	// 2 [1 -> null]
	// 3 [4 -> null]
	// 4 [3 -> null]

	// 0----1----2   3----4

	// [true false false false false]
}