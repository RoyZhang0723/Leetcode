/**
 *
 * https://leetcode.cn/problems/frog-jump/description/?envType=study-plan-v2&envId=dynamic-programming-grandmaster
 * 动态规划好题
 * 青蛙过河
 */
public class CanCross {
    /**
     *
     * 动态规划题目不管是多维还是一维，首先看这个转移的过程中有几个决定性的变化的量，如果是一个那么就用一维数组来做，多个就是多维数组
     * 其次是考虑动态转移方程，也就是前一个和后一个的递归关系，可以带入特殊值来进行表示
     * 最后是选择遍历的方式，从起始值开始一步一步通过递推关系得到之后的问题解
     * @param stones
     * @return
     */
    public boolean canCross(int[] stones) {
        int n = stones.length;
        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true;
        //两个之间间隔超过可以增长到的最长情况时，直接返回false
        for (int i = 1; i < n; i++) {
            if (stones[i] - stones[i - 1] > i) {
                return false;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                //这个写的方法需要反复揣摩，遍历找到前一个可能到达的点
                int k = stones[i] - stones[j];
                if (k > j + 1) {
                    break;
                }
                //还是要在纸上写好动态转移方程
                dp[i][k] = dp[j][k] || dp[j][k - 1] || dp[j][k + 1];
                if (i == n - 1 && dp[i][k]) {
                    return true;
                }
            }
        }
        return false;
    }
}
