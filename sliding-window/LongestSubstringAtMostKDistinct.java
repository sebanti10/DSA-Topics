// Given a string, find the length of the longest substring T that contains
// at most k distinct characters.
// Example:
// Input: str = “eceba” and k = 2,
// Output: 3
// Explanation: T is "ece" which its length is 3.

import java.util.*;

public class LongestSubstringAtMostKDistinct {
    public static int findLongestSubstring(String str, int k) {
        HashMap<Character, Integer> charFrequencyMap = new HashMap<Character, Integer>();
        int maxLength = 0;
        for (int windowStart = 0, windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);

            charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);

            while (charFrequencyMap.size() > k) {
                char leftChar = str.charAt(windowStart++);
                charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
                if (charFrequencyMap.get(leftChar) == 0)
                    charFrequencyMap.remove(leftChar);
                // windowStart++;
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(findLongestSubstring("eceba", 3));
    }
}
