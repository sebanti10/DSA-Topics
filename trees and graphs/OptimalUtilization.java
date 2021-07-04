// Given 2 lists a and b.
// Each element is a pair of integers where the first integer represents
// the unique id and the second integer represents a value. Your task is to
// find an element from a and an element form b such that the sum of their values
// is less or equal to target and as close to target as possible.
// Return a list of ids of selected elements. If no pair is possible, return an empty list.

// Example 1:

// Input:
// a = [[1, 2], [2, 4], [3, 6]]
// b = [[1, 2]]
// target = 7

// Output: [[2, 1]]

// Explanation:
// There are only three combinations [1, 1], [2, 1], and [3, 1],
// which have a total sum of 4, 6 and 8, respectively.
// Since 6 is the largest sum that does not exceed 7, [2, 1] is the optimal pair.
// Example 2:

// Input:
// a = [[1, 3], [2, 5], [3, 7], [4, 10]]
// b = [[1, 2], [2, 3], [3, 4], [4, 5]]
// target = 10

// Output: [[2, 4], [3, 2]]

// Explanation:
// There are two pairs possible. Element with id = 2 from the list `a` has a value 5,
// and element with id = 4 from the list `b` also has a value 5.
// Combined, they add up to 10. Similarily, element with id = 3 from `a` has a value 7,
// and element with id = 2 from `b` has a value 3.
// These also add up to 10. Therefore, the optimal pairs are [2, 4] and [3, 2].

import java.util.*;

class OptimalUtilization {
	public static List<List<Integer>> compute(int[][] a, int[][] b, int target) {
		Arrays.sort(a, (int[] a1, int[] a2) -> a1[1] - a2[1]);
		Arrays.sort(b, (int[] b1, int[] b2) -> b1[1] - b2[1]);

		List<List<Integer>> optimalPairs = new ArrayList<List<Integer>>();
		int aEnd = a.length - 1;
		int bStart = 0;
		int max = Integer.MIN_VALUE;

		while (aEnd >= 0 && bStart < b.length) {
			if (a[aEnd][1] + b[bStart][1] > target)
				aEnd--;
			else {
				if (a[aEnd][1] + b[bStart][1] > max) {
					max = a[aEnd][1] + b[bStart][1];
					optimalPairs.clear();
					optimalPairs.add(Arrays.asList(a[aEnd][0], b[bStart][0]));
				} else if (a[aEnd][1] + b[bStart][1] == max)
					optimalPairs.add(Arrays.asList(a[aEnd][0], b[bStart][0]));
				bStart++;
			}
		}
		return optimalPairs;
	}

	public static void main(String[] args) {
		int[][][] As = {
			{{1, 2}, {2, 4}, {3, 6}},
			{{1, 3}, {2, 5}, {3, 7}, {4, 10}},
			{{1, 8}, {2, 7}, {3, 14}},
			{{1, 8}, {2, 15}, {3, 9}}
		};

		int[][][] Bs = {
			{{1, 2}},
			{{1, 2}, {2, 3}, {3, 4}, {4, 5}},
			{{1, 5}, {2, 10}, {3, 14}},
			{{1, 8}, {2, 11}, {3, 12}}
		};

		int[] targets = {7, 10, 20, 20};

		for (int i = 0; i < As.length; i++) {
			System.out.println(compute(As[i], Bs[i], targets[i]).toString());
		}
	}
}