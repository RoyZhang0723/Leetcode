import java.util.ArrayList;
import java.util.List;

/**
 * 最长回文子串
 */
public class LongestPalindrome {
    /**
     * 解法一：暴力法
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        // write your code here
        List startList = new ArrayList<Integer>();
        List endList = new ArrayList<Integer>();
        int len = s.length();
        int max = 0;
        int start = 0;
        int end = 0;
        if (len <= 1) {
            return s;
        }
        for (int i = 0; i < len; i ++) {
            for (int j = i; j < len; j ++) {
                if (isPalindrome(s, i, j)) {
                    startList.add(i);
                    endList.add(j);
                }
            }
        }
        for (int i = 0; i < startList.size(); i ++) {
            if ((int)endList.get(i) - (int)startList.get(i) > max) {
                max = (int)endList.get(i) - (int)startList.get(i);
                start = (int)startList.get(i);
                end = (int)endList.get(i);
            }
        }
        return s.substring(start,end + 1);
    }

    /**
     * 解法二：动态规划
     * @param s
     * @return
     */
    public static String longestPalindromeDP(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public static boolean isPalindrome(String str, int start, int end) {
        if (end - start + 1 == 2) {
            if (str.charAt(start) == str.charAt(end)) {
                return true;
            } else {
                return false;
            }
        } else if (end - start == 0) {
            return true;
        } else {
            if (str.charAt(start) == str.charAt(end)) {
                return isPalindrome(str, start + 1, end - 1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcdadcab"));
        System.out.println(longestPalindrome("aba"));
    }
}
