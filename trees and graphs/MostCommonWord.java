// Leetcode 819. Most Common Word

// Given a string paragraph and a string array of the banned words banned,
// return the most frequent word that is not banned. It is guaranteed there is at least
// one word that is not banned, and that the answer is unique.

// The words in paragraph are case-insensitive and the answer
// should be returned in lowercase.
 

// Example 1:

// Input: paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
// banned = ["hit"]

// Output: "ball"

// Explanation: 
// "hit" occurs 3 times, but it is a banned word.
// "ball" occurs twice (and no other word does), so it is the most
// frequent non-banned word in the paragraph. 
// Note that words in the paragraph are not case sensitive,
// that punctuation is ignored (even if adjacent to words, such as "ball,"), 
// and that "hit" isn't the answer even though it occurs more because it is banned.

// Example 2:

// Input: paragraph = "a.", banned = []
// Output: "a"


class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        String[] words = paragraph.replaceAll("[^a-zA-Z0-9]", " ").toLowerCase().split("\\s+");
        Set<String> uniqueBannedWords = new HashSet<>();
        
        for (String bannedWord: banned)
            uniqueBannedWords.add(bannedWord);
        
        HashMap<String, Integer> freq = new HashMap<>();
        
        for (String word: words)
            if (!uniqueBannedWords.contains(word))
                freq.put(word, freq.getOrDefault(word, 0) + 1);
        
        int highestFrequency = Integer.MIN_VALUE;
        String mostCommon = "";
        
        for (Map.Entry<String, Integer> wordKeySet: freq.entrySet()) {
            if (wordKeySet.getValue() > highestFrequency) {
                highestFrequency = wordKeySet.getValue();
                mostCommon = wordKeySet.getKey();
            }
        }
        
        return mostCommon;
    }
}