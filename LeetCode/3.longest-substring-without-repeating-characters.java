/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 *
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 * algorithms
 * Medium (33.81%)
 * Likes:    31318
 * Dislikes: 1363
 * Total Accepted:    4.2M
 * Total Submissions: 12.3M
 * Testcase Example:  '"abcabcbb"'
 *
 * Given a string s, find the length of the longest substring without repeating
 * characters.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not
 * a substring.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols and spaces.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s==null || s.length() == 0) {
            return 0;
        }
        // array index is the char, value is the index in string s.
        Integer[] memo = new Integer[128];
        // substring's window index in the whole string
        int left = 0;
        int right = 0;
        memo[s.charAt(left)] = left;
        int maxLen = 1;
        int currLen = 1;
        char currChar = s.charAt(left);
        // iterate all the characters in loop
        for (right = 1; right < s.length(); right++) {
            currChar = s.charAt(right);
            // current character doesn't exist in memo, 
            // means it's valid, add the index and max length add one.
            if (memo[currChar] == null) {
                memo[currChar] = right;
                currLen++;
                maxLen = Math.max(maxLen, currLen);
            } else {
                // this character has existed, 
                // remove all the chararcters from memo from the left index to the existing one (including it).
                // and re-count from the existing index + 1.
                for (int j = left; j < memo[currChar]; j++) {
                    memo[s.charAt(j)] = null;
                }
                left = memo[currChar] + 1;
                memo[currChar] = right;
                currLen = right - left + 1;
            }
        }

        return maxLen;
    }
}
class test {
    public static void main(String[] args) {
        var solution = new Solution();
        var result = solution.lengthOfLongestSubstring("pwwkewabcabcdefgfffff");
        System.out.println(result);
    }
}

// @lc code=end