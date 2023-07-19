/**
 *
 * https://leetcode.cn/problems/strange-printer/description/?envType=study-plan-v2&envId=dynamic-programming-grandmaster
 * 奇怪的打印机
 * 动态规划好题
 */
public class StrangePrinter {
    /**
     * 仍然需要强调的是状态转移方程的确立，拿到题目需要认真研究子问题之间
     * @param s
     * @return
     */
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            f[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i][j - 1];
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        min = Math.min(min, f[i][k] + f[k + 1][j]);
                    }
                    f[i][j] = min;
                }
            }
        }
        return f[0][n - 1];
    }
}
