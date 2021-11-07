import java.util.Arrays;

/**
 * 最长递增子序列（动态规划）
 *
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *
 */
public class LengthOfLIS {
    public static int lengthOfLIS(int[] nums) {
        int ans = 0;
        if (nums.length <= 1) {
            return nums.length;
        }
        int[] dp = new int[nums.length];
        // dp[i]为下标为i时候的最长递增子序列（dp数组的含义十分重要）
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                // 借助数学归纳法的思想，先赋予一个具体size的问题去思考，然后找到一个可以递推下去的方法
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (dp[i] > ans) {
                ans = dp[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }
}
