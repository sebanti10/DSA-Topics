// Leetcode 692. Top K Frequent Words

// Given a non-empty list of words, return the k most frequent elements.

// Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

// Example 1:
// Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
// Output: ["i", "love"]
// Explanation: "i" and "love" are the two most frequent words.
//     Note that "i" comes before "love" due to a lower alphabetical order.


// Example 2:
// Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
// Output: ["the", "is", "sunny", "day"]
// Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
//     with the number of occurrence being 4, 3, 2 and 1 respectively.
// Note:
// You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
// Input words contain only lowercase letters.
// Follow up:
// Try to solve it in O(n log k) time and O(n) extra space.


class Solution {
    public List<String> topKFrequent(String[] words, int k) {
//         if (words.length == k)
//             return Arrays.asList(words);
            
        HashMap<String, Integer> freq = new HashMap<>();
        
        for(String word: words)
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        
        Queue<String> elementPriority = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                if (freq.get(str1) < freq.get(str2))
                    return -1;
                else if (freq.get(str1) > freq.get(str2))
                    return 1;
                else
                    // in highest to lowest lower order comes first
                    // so when storing in ascending order put it in opposite order
                    // so if something does get chop off
                    //it's the one with higher alphabetical value
                    return str2.compareTo(str1);
            }
        });
        
        for (String el: freq.keySet()) {
            elementPriority.add(el);
            
            if (elementPriority.size() > k)
                elementPriority.poll();
        }
        
        List<String> resultKFrequent = new ArrayList<>();
        
        for (int i = 0; i < k; i++)
            resultKFrequent.add(0, elementPriority.poll());
        
        return resultKFrequent;
    }
}