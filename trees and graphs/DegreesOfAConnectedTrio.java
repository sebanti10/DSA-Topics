// Leetcode 1761

class Solution {
    public int minTrioDegree(int n, int[][] edges) {
//         Degrees: Keeps track of total degree for each Node
        
//         For edge (i,j), there is a trio if there is a vertex, 'k',
//         which has edges with i as well as j: (i,k), (j, k).
        
//         If (i,j,k) are a trio each contribute (2 + 2 + 2) = 6 edges to each other.
//         Degree of a conntected trio = (degree(i) + degree(j) + degree(k)) - 6.
//         Take minimum of all instances
        
//         1 3
//         2 3
//         3 3
//         4 1
//         5 1
//         6 1
            
//         1 2 3 4 5 6
//      1  0 1 1 1 0 0
//      2  1 0 1 0 1 0
//      3  1 1 0 0 0 1
//      4  1 0 0 0 0 0 
//      5  0 1 0 0 0 0
//      6  0 0 1 0 0 0
        
        HashMap<Integer, Integer> degrees = new HashMap<>();
        boolean[][] adjMatrix = new boolean[n + 1][n + 1];
        int minDegreeConnectedTrio = Integer.MAX_VALUE;
        
        for (int[] edge: edges) {
            degrees.put(edge[0], degrees.getOrDefault(edge[0], 0) + 1);
            degrees.put(edge[1], degrees.getOrDefault(edge[1], 0) + 1);
            adjMatrix[edge[0]][edge[1]] = true;
            adjMatrix[edge[1]][edge[0]] = true;
        }
        
        for (int[] edge: edges) {
            for (int i = 1; i <= n; i++) {
                if (i != edge[0] && i != edge[1] && adjMatrix[i][edge[0]] && adjMatrix[i][edge[1]])
                    minDegreeConnectedTrio = Math.min(minDegreeConnectedTrio, degrees.get(edge[0]) + degrees.get(edge[1]) + degrees.get(i) - 6);
            }
        }
        return minDegreeConnectedTrio == Integer.MAX_VALUE ? -1 : minDegreeConnectedTrio;
    }
}