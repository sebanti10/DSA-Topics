import java.util.HashMap;

public class CountSubarraysExactlyKDistinct {
    public static int subarraysWithKDistinct(int[] A, int k) {
        // subarrays with exactly k distinct elements can be written in form of at most
        // k:
        // exactly(k) = atMost(k) - atMost(k - 1)
        return atMostKDistinct(A, k) - atMostKDistinct(A, k - 1);
    }

    public static int atMostKDistinct(int[] A, int k) {
        int n = A.length;
        int noOfSubarrays = 0;
        HashMap<Integer, Integer> freqMap = new HashMap<Integer, Integer>();

        for (int windowStart = 0, windowEnd = 0; windowEnd < n; windowEnd++) {
            freqMap.put(A[windowEnd], freqMap.getOrDefault(A[windowEnd], 0) + 1);

            // while no of distinct elements exceeds k, shrink window
            while (freqMap.size() > k) {
                freqMap.put(A[windowStart], freqMap.get(A[windowStart]) - 1);
                if (freqMap.get(A[windowStart]) == 0)
                    freqMap.remove(A[windowStart]);
                windowStart++;
            }

            // if [L,R] contains at most k distinct elements,
            // all of its subarrays also contains at most k distinct elements
            // all possible subarrays within [L,R] can be found by its length: (R - L + 1)
            noOfSubarrays += windowEnd - windowStart + 1;
        }
        return noOfSubarrays;
    }

    public static void main(String[] args) {
        System.out.println(subarraysWithKDistinct(new int[] { 1, 2, 1, 2, 3 }, 2));
    }
}
