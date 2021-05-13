import java.util.*;

public class LongestRepeatingCharacter {
    public static int characterReplacement(String s, int k) {
        int n = s.length();
        int maxCount = 0, maxLength = 0;
        HashMap<Character, Integer> charFrequencyMap = new HashMap<Character, Integer>();

        for (int windowStart = 0, windowEnd = 0; windowEnd < n; windowEnd++) {
            char rightChar = s.charAt(windowEnd);
            charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);

            // counts the most frequent character- the probable longest repeating character
            maxCount = Math.max(maxCount, charFrequencyMap.get(rightChar));

            // while (total longest repeating substring - maxCount > k)
            // i.e. the character to be replaced appears more than k times
            // we need to shrink the window
            while (windowEnd - windowStart + 1 - maxCount > k) {
                char leftChar = s.charAt(windowStart++);
                charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
                if (charFrequencyMap.get(leftChar) == 0)
                    charFrequencyMap.remove(leftChar);
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABBA", 2));
    }
}