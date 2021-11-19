/**
 * https://leetcode-cn.com/problems/maximum-subarray/
 * 最大子序列 （动态规划）
 * 关键是要体会这一题中dp数组定义时候的思考和技巧
 */
public class MaxSubArray {
    public static int maxSubArray(int[] nums) {
        // dp数组在这里的定义是以nums[i]为结尾的子序列的最大值
        int[] dp = new int[nums.length];
        int ans = Integer.MIN_VALUE;
        // base case
        for (int i = 0; i < nums.length; i++) {
            dp[i] = nums[i];
        }
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (ans < dp[i]) {
                ans = dp[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }
}
