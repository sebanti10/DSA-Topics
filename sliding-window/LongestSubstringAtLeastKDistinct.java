import java.util.HashMap;

public class LongestSubstringAtLeastKDistinct {
    public static int longestSubstring(String s, int k) {
        int n = s.length();

        // First we need to know the no of max. unique letters in the string
        // Example : s = "caaabbdee", k = 2
        // maxUnique = 5, i.e., ['a', 'b', 'c', 'd', 'e']
        int maxUnique = getMaxUniqueLetters(s);
        int longestSubstring = 0;

        // we break the problem into subproblems of number of unique letters
        // at each interval, we find the longest substring with "currentUnique" no of
        // letters
        // for maxUnique = 5, we break the problem into subproblems as:
        // longest substring when no of unique letters can be = [1...5]
        // so, when currentUnique = 1, if only 1 unique letter was allowed, what is
        // the longest possible substring we could have where each unique letter would
        // appear at least k (=2) times?
        for (int currentUnique = 1; currentUnique <= maxUnique; currentUnique++) {

            // within currentUnique also we break it into unique = [0...currentUnique]
            int unique = 0, countAtLeastK = 0;
            HashMap<Character, Integer> freqMap = new HashMap<Character, Integer>();
            int windowStart = 0, windowEnd = 0;
            while (windowEnd < n) {
                // at each loop interval, we only allow currentUnique no of "unique" characters
                // if the condition breaks, we shrink the window accordingly
                if (unique <= currentUnique) {
                    char rightChar = s.charAt(windowEnd);

                    // if the character appears for the first time, e.g., {c: 1}
                    // we increment "unique" - as it appeared for the first time
                    if (freqMap.getOrDefault(rightChar, 0) == 0)
                        unique++;
                    freqMap.put(rightChar, freqMap.getOrDefault(rightChar, 0) + 1);

                    // if the character has appeared exactly k times, we can increment countAtLeastK
                    // as for this character it has satisfied the condition
                    if (freqMap.get(rightChar) == k)
                        countAtLeastK++;

                    // only if window is being increased do we have to incrememnt windowEnd
                    // this is why we use a while loop instead of a for
                    // where windowEnd would have been incremented regardless
                    windowEnd++;
                } else {
                    char leftChar = s.charAt(windowStart);

                    // if the character to be removed from the sliding window satisfied
                    // the condition of appearing at least k times, we decrement countAtLeastK
                    if (freqMap.get(leftChar) == k)
                        countAtLeastK--;

                    freqMap.put(leftChar, freqMap.get(leftChar) - 1);

                    // if it appeared only once
                    // removing it means losing a "unique" character
                    if (freqMap.get(leftChar) == 0) {
                        unique--;
                        freqMap.remove(leftChar);
                    }

                    // we increment the windowStart, i.e., shrinking the sliding window
                    windowStart++;
                }

                // if no of unique characters are what we expected from this loop,
                // i.e., currentUnique no of times and all these unique characters
                // satisfies the condition of appeating at least k times
                // we can update the length
                if (unique == currentUnique && unique == countAtLeastK)
                    // we use (windowEnd - windowStart) instead of the usual
                    // (windowEnd - windowStart + 1) because windowEnd gets incremented anyway
                    longestSubstring = Math.max(longestSubstring, windowEnd - windowStart);
            }
        }
        return longestSubstring;
    }

    public static int getMaxUniqueLetters(String s) {
        int n = s.length();
        HashMap<Character, Integer> freqMap = new HashMap<Character, Integer>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        return freqMap.size();
    }

    public static void main(String[] args) {
        System.out.println(longestSubstring("caaabbdee", 2));
    }
}
