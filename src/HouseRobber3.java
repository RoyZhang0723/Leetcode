/**
 * https://www.lintcode.com/problem/535/description?_from=cat
 * 打劫房屋（动态规划）
 */
public class HouseRobber3 {
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
