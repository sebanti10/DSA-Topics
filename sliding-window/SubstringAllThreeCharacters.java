import java.util.Arrays;

public class SubstringAllThreeCharacters {
    public static int numberOfSubstrings(String s) {
        int n = s.length();
        int noOfSubstrings = 0;
        int[] abc = { 0, 0, 0 };
        for (int windowStart = 0, windowEnd = 0; windowEnd < n; windowEnd++) {
            char rightChar = s.charAt(windowEnd);
            abc[rightChar - 'a']++;

            while (abc[0] > 0 && abc[1] > 0 && abc[2] > 0) {
                char leftChar = s.charAt(windowStart);
                abc[leftChar - 'a']--;
                windowStart++;
            }
            noOfSubstrings += windowStart;
        }
        return noOfSubstrings;
    }

    public static void main(String[] args) {
        System.out.println(numberOfSubstrings("aaacb"));
    }
}
