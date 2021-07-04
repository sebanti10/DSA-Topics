import java.util.*;

class AutoscalePolicyUtilization {
	public static int finalInstances (int instances, int[] averageUtil) {
		for (int i = 0; i < averageUtil.length; i++) {
			if (averageUtil[i] < 25 && instances > 1) {
				instances = (instances + 1) / 2;
				i += 10;
			} else if (averageUtil[i] > 60 && instances * 2 <= 200000000) {
				instances *= 2;
				i += 10;
			}
		}
		return instances;
	}

	public static void main(String[] args) {
		int[] instances = new int[] {2, 1, 5};
		int[][] averageUtil = new int[][] {
			{25, 23, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 76, 80},
			{5, 10, 80},
			{30, 5, 4, 8, 19, 89},
		};

		for (int i = 0; i < instances.length; i++)
			System.out.println(finalInstances(instances[i], averageUtil[i]));
	}
}