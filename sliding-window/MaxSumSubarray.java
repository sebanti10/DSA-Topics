/*
* Find the max sum subarray of a fixed size K
*
* Example input: [4, 2, 1, 7, 8, 1, 2, 8, 1, 0]
*
* Example output: 16
*
*/

public class MaxSumSubarray {

    public static int findMaxSumSubarray(int[] A, int k) {
        int maxValue = Integer.MIN_VALUE;
        int currentRunningSum = 0;

        for (int i = 0; i < A.length; i++) {
            // keep adding a new element
            currentRunningSum += A[i];

            // if index has reached the sliding window length
            if (i >= k - 1) {
                maxValue = Math.max(maxValue, currentRunningSum);
                // remove the element just left of the current window,
                // having which will violate the window size condition
                currentRunningSum -= A[i - k + 1];
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        System.out.println(findMaxSumSubarray(new int[] { 4, 2, 1, 7, 8, 1, 2, 8, 1, 0 }, 3));
    }
}