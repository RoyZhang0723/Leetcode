/**
 * https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/?envType=study-plan-v2&envId=coding-interviews
 * 把数字翻译成字符串
 *
 * 动态规划枚举计数，非常有代表性的一道题
 *
 */

public class TranslateNum {
    public int translateNum(int num) {
        String s = num + "";
        int n = s.length();
        // 定义：dp[i]表示s[0..i-1]的解码方式数量
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            char c = s.charAt(i - 1), d = s.charAt(i - 2);
            if ('0' <= c && c <= '9') {
                // 1.s[i] 本身可以作为一个字母
                dp[i] += dp[i - 1];
            }
            if (d == '1' || d == '2' && c <= '5') {
                // 2.s[i]和s[i-1]结合起来表示一个字母
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}
