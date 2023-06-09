/**
 * https://leetcode.cn/problems/distinct-subsequences/description/
 * 不同的子序列
 */

import java.util.Arrays;

public class NumDistinct {
    //备忘录，用空间换时间
    int[][] memo;

    public int numDistinct(String s, String t) {
        memo = new int[s.length()][t.length()];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(s, 0, t, 0);
    }

    /**
     * 定义：s[i...]的子序列中t[j..]出现的次数为dp(s,i,t,j)
     *
     * @param s
     * @param i
     * @param t
     * @param j
     * @return
     */
    public int dp(String s, int i, String t, int j) {
        // base case 1
        if (j == t.length()) {
            return 1;
        }
        // base case 2
        if (s.length() - i < t.length() - j) {
            return 0;
        }
        // 查备忘录防止冗余计算
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = 0;

        //执行状态转移方程
        if (s.charAt(i) == t.charAt(j)) {
            // 匹配，两种情况，累加关系
            res += dp(s, i + 1, t, j + 1) + dp(s, i + 1, t, j);
        } else {
            // 不匹配，在s[i+1...]的子序列中计算t[j...]的出现次数
            res += dp(s, i + 1, t, j);
        }
        memo[i][j] = res;
        return res;
    }
}
