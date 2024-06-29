//Approach 1 : Two pointers
// Time Complexity : O(2*n^2), n for traversing the string and n each for odd and even length check
// Space Complexity :  O(1) 
// Did this code successfully run on Leetcode : yes
//Approach : Extend around the center on both sides for both even and odd length palindrome.

class Solution {
    private int start;
    private int end;
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return s;
        int n = s.length();

        for(int i = 0; i < n ; i++){
            //odd length
            checkPalindrome(s,i,i);
            //even length
            if(i != n-1 && s.charAt(i) == s.charAt(i+1)){
                checkPalindrome(s,i,i+1);
            }
        }
        return s.substring(start, end+1);
    }

    //extend to both sides 
    private void checkPalindrome(String s, int left , int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        //actual palindrome position
        left++;
        right--;

        if(right - left > end - start){
            start = left;
            end = right;
        }
    }
}

//Approach 2: dp

// Time Complexity : O(n^2)
// Space Complexity :  O(n^2) 
// Did this code successfully run on Leetcode : yes
//Approach : check all the substrings and use dp to save repeated calculations

class Solution {

    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return s;
        int n = s.length();

        boolean [][] dp = new boolean[n][n];
        dp[0][0] = true;

        int start = 0; int end = 0;

        for(int i = 0 ; i < n ; i++){
            for(int j = i ; j >= 0 ; j--){
                if(s.charAt(i) == s.charAt(j)){
                    if(i - j < 2 || dp[i-1][j+1]){ // if string length is < 2, there is no in between string.
                        dp[i][j] = true; 
                        if(end - start < i - j){
                            end = i;
                            start =j;
                        }
                    }

                }else{
                    dp[i][j] = false;
                }
            }
        }

        return s.substring(start,end+1);
    }
}

//Space optimization dp
// Time Complexity : O(n^2)
// Space Complexity :  O(n) 
class Solution {

    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return s;
        int n = s.length();

        boolean [] dp = new boolean[n];
        dp[0] = true;

        int start = 0; int end = 0;

        for(int i = 1 ; i < n ; i++){
            boolean diagUp = false;
            for(int j = i ; j >= 0 ; j--){
                boolean temp = dp[j];
                if(s.charAt(i) == s.charAt(j)){
                    if(i - j < 2 || diagUp){ // if string length is < 2, there is no in between string.
                        dp[j] = true; 
                        if(end - start < i - j){
                            end = i;
                            start =j;
                        }
                    }
                    else{
                    dp[j] = false;
                }
                }else{
                    dp[j] = false;
                }
                diagUp = temp;
            }
        }

        return s.substring(start,end+1);
    }
}
