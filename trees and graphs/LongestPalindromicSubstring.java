class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (s == null || n <= 0)
            return "";
        boolean[][] dp = new boolean[n][n];
        
        int maxLength = 1;
        int maxIndex = 0;
        
        for (int i = 0; i < n; i++)
            dp[i][i] = true;
        
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                maxLength = 2;
                maxIndex = i;
            } else {
                dp[i][i + 1] = false;
            }
        }
        
        for (int k = 3; k <= n; k++) {
            for (int i = 0; i < n - k + 1; i++) {
                int j = i + k - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == true) {
                    dp[i][j] = true;
                    if (k > maxLength) {
                        maxLength = k;
                        maxIndex = i;
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return s.substring(maxIndex, maxIndex + maxLength);
    }
}

// 00 11 22 33 44
//    01 12 23 34
//       02 13 24
//          03 14
//             04

// 02 -> bab
// 0 == 2, 11
// 0 3
// 13 -> aba
// 1 3

//     b a b a d
//     0 1 2 3 4
// b 0 T F T F F
// a 1 F T F T F
// b 2 F F T F F
// a 3 F F F T F
// d 4 F F F F T