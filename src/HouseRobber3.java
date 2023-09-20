import java.util.Arrays;

/**
 *
 * https://leetcode.cn/problems/house-robber/ （打家劫舍I）
 *https://leetcode.cn/problems/house-robber-ii/description/?envType=study-plan-v2&envId=bytedance-2023-spring-sprint （打家劫舍II）
 *
 * https://www.lintcode.com/problem/535/description?_from=cat （打家劫舍III）
 *
 *
 * 打家劫舍（动态规划）
 */
public class HouseRobber3 {
    public static int rob2(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        int[] memo1 = new int[n];
        int[] memo2 = new int[n];
        Arrays.fill(memo1, -1);
        Arrays.fill(memo2, -1);
        // 两次调用使用两个不同的备忘录
        return Math.max(
                dp(nums, 0, n - 2, memo1),
                dp(nums, 1, n - 1, memo2)
        );
    }

    // 定义：计算闭区间 [start,end] 的最优结果
    public static int dp(int[] nums, int start, int end, int[] memo) {
        if (start > end) {
            return 0;
        }

        if (memo[start] != -1) {
            return memo[start];
        }
        // 状态转移方程
        int res = Math.max(
                dp(nums, start + 2, end, memo) + nums[start],
                dp(nums, start + 1, end, memo)
        );

        memo[start] = res;
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {6, 6, 4, 8, 4, 3, 3, 10};
        System.out.println(rob2(nums));
    }



    public static int houseRobber3(TreeNode root) {
        int[] ans = dp(root);
        return Math.max(ans[0], ans[1]);
    }

    /**
     * dp[i][0] 表示以i为根的子树不偷根节点能获得的最高价值，dp[i][1]表示以i为根的子树偷根节点能获得的最高价值
     *
     * @param root
     * @return
     */
    private static int[] dp(TreeNode root) {
        if (root == null) {
            int[] now = new int[]{0, 0};
            return now;
        }
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        int[] now = new int[2];
        now[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        now[1] = left[0] + right[0] + root.val;
        return now;
    }
}
