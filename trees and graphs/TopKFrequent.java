// Leetocde 347. Top K Frequent Elements

// Given an integer array nums and an integer k, return the k most frequent elements.
// You may return the answer in any order.

// Example 1:

// Input: nums = [1,1,1,2,2,3], k = 2
// Output: [1,2]
// Example 2:

// Input: nums = [1], k = 1
// Output: [1]


class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
            
        int n = nums.length;
        
        if (n == k)
            return nums;
        
        for (int i = 0; i < n; i++)
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        
        Queue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer in1, Integer in2) {
                return freq.get(in2) - freq.get(in1);
            }
        });
        
        for (Map.Entry<Integer, Integer> element: freq.entrySet()) {
            pq.add(element.getKey());
        }
        
        int[] topK = new int[k];
        
        for (int i = 0; i < k; i++)
            topK[i] = pq.poll();
        
        return topK;
        
    }
}