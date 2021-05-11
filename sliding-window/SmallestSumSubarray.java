// Find the smallest subarray that exists within the given array
// where the sum of values is greater than or equal to a given sum.

// Example:

// Input: A = [4, 2, 2, 7, 8, 1, 2, 8, 1, 0], smallestSum = 8
// Output: 1
// Explanation: 

// Window position             Sum (>=8)     Smallest Subarray size
// ---------------             ---------     ----------------------
// [4 2 2] 7 8 1 2 8 1 0            8                   3
// 4 [2 2 7] 8 1 2 8 1 0            11                  3
// 4 2 [2 7] 8 1 2 8 1 0            9                   2
// 4 2 2 [7 8] 1 2 8 1 0            15                  2
// 4 2 2 7 [8] 1 2 8 1 0            8                   1                   

public class SmallestSumSubarray {
    public static int smallestSubarray(int[] A, int targetSum) {
        int n = A.length;
        int currentWindowSum = 0;
        int minWindowSize = Integer.MAX_VALUE;

        for (int windowStart = 0, windowEnd = 0; windowEnd < n; windowEnd++) {
            currentWindowSum += A[windowEnd];

            while (currentWindowSum >= targetSum) {
                minWindowSize = Math.min(minWindowSize, windowEnd - windowStart + 1);
                currentWindowSum -= A[windowStart++];
            }
        }
        return minWindowSize;
    }

    public static void main(String[] args) {
        System.out.println(smallestSubarray(new int[] { 4, 2, 2, 7, 8, 1, 2, 8, 1, 0 }, 8));
    }
}
