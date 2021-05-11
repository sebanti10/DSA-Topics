import java.util.Deque;

// Given an array arr[] of size N and an integer k.
// Find the maximum for each and every contiguous subarray of size k.

// Example 1:

// Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
// Output: [3,3,5,5,6,7]
// Explanation: 
// Window position                Max
// ---------------               -----
// [1  3  -1] -3  5  3  6  7       3
//  1 [3  -1  -3] 5  3  6  7       3
//  1  3 [-1  -3  5] 3  6  7       5
//  1  3  -1 [-3  5  3] 6  7       5
//  1  3  -1  -3 [5  3  6] 7       6
//  1  3  -1  -3  5 [3  6  7]      7

// Constraints:

// 1 <= nums.length <= 105
// -104 <= nums[i] <= 104
// 1 <= k <= nums.length

import java.util.*;

public class SlidingWindowMaximum {
    public static int[] maxSlidingWindow(int[] A, int k) {
        int n = A.length;
        int[] output = new int[n - k + 1];
        Deque<Integer> dq = new LinkedList<>();
        int i = 0;

        for (; i < k; i++) {
            while (!dq.isEmpty() && A[i] >= A[dq.peekLast()])
                dq.removeLast();
            dq.addLast(i);
        }

        for (; i < n; i++) {
            output[i - k] = A[dq.peekFirst()];

            while (!dq.isEmpty() && dq.peekFirst() <= i - k)
                dq.removeFirst();

            while (!dq.isEmpty() && A[i] >= A[dq.peekLast()])
                dq.removeLast();
            dq.addLast(i);
        }
        output[i - k] = A[dq.peekFirst()];
        return output;
    }

    public static void main(String[] args) {
        int[] output = maxSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3);
        for (int num : output)
            System.out.print(num + " ");
    }
}
