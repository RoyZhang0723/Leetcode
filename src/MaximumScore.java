/**
 *
 * https://leetcode.cn/problems/maximum-score-from-performing-multiplication-operations/description/?envType=study-plan-v2&envId=dynamic-programming-grandmaster
 * 执行乘法运算的最大分数
 *
 */
public class MaximumScore {
    /**
     * 定义二维数组dp[m + 1][m + 1]。dp[i][j]：表示nums数组中前i个数和后j个数组成的最大分数。
     * base case：
     * dp[0][0] = 0;
     * dp[i][0]：此状态表示nums数组中前i个数和后0个数组成的最大分数，此状态只可能由dp[i - 1][0]转移得到。
     * dp[0][j]：此状态表示nums数组中前0个数和后j个数组成的最大分数，此状态只可能由dp[0][j - 1]转移得到。
     * dp[i][j]：该状态表示nums数组中前i个数和后j个数组成的最大分数，可能由状态dp[i - 1][j]和状态dp[i][j - 1]转移得到，取其中得分最大的一个。
     * 遍历所有可能的组合（满足i + j = m）获得最大得分。
     *
     * 要想清楚状态转移方程
     *
     * @param nums
     * @param multipliers
     * @return
     */
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length,m = multipliers.length;
        int[][] dp = new int[1000 + 5][1000 + 5];
        dp[0][0] = 0;
        for (int i = 1;i <= m;++i) dp[i][0] = dp[i - 1][0] + nums[i - 1] * multipliers[i - 1];
        for (int j = 1;j <= m;++j) dp[0][j] = dp[0][j - 1] + nums[n - j] * multipliers[j - 1];
        for (int i = 1;i <= m;++i){
            for (int j = 1;i + j <= m;++j){
                dp[i][j] = Math.max(dp[i - 1][j] + nums[i - 1] * multipliers[i + j - 1],dp[i][j - 1] + nums[n - j] * multipliers[i + j - 1]);
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0;i <= m;++i) ans = Math.max(ans,dp[i][m - i]);
        return ans;
    }
}
