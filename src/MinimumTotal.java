/**
 *
 * https://www.lintcode.com/problem/109
 * 数字三角形
 * 动态规划用滚动数组优化的好例子
 *
 */
public class MinimumTotal {

    /**
     * dp的基本写法
     * @param triangle
     * @return
     */
    public static int minimumTotal(int[][] triangle) {
        // write your code here
        int n = triangle.length;
        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];
        int i, j;
        int ans;
        for (i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
            for (j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
        }
        ans = dp[n - 1][0];
        for (j = 1; j < n; j++) {
            if (ans > dp[n - 1][j]) {
                ans = dp[n - 1][j];
            }
        }
        return ans;
    }

    /**
     * 空间复杂度上面进行了优化，因为每次在更新的时候只需要两行的数据，故之前的数据实际上没有记住的必要的
     * @param triangle
     * @return
     */
    public static int minimumTotal1(int[][] triangle) {
        if (triangle == null || triangle.length == 0 || triangle[0] == null || triangle[0].length == 0) {
            return -1;
        }

        // 三角形边长为n，储存在n行n列矩阵
        int n = triangle.length;

        // 状态（State）：dp[x][y] = 从三角形顶点(0,0)到(x,y)的最小路径和
        int[][] dp = new int[2][n];

        // 初始化(Initialization)
        // dp[0][0]点的最短路径和为三角形中(0,0)点的值
        dp[0][0] = triangle[0][0];

        // 自顶向下的方式的多重循环动态规划
        for (int i = 1; i < n; i++) {
            // 初始化本行的最左点与最右点
            dp[i % 2][0] = dp[(i - 1) % 2][0] + triangle[i][0];
            dp[i % 2][i] = dp[(i - 1) % 2][i - 1] + triangle[i][i];
            for (int j = 1; j < i; j++) {
                // 方程（Function）：
                // dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j]
                // 通过上层左右两点推得当前点点最小路径
                dp[i % 2][j] = Math.min(dp[(i - 1) % 2][j - 1], dp[(i - 1) % 2][j]) + triangle[i][j];
            }
        }

        // 答案（Answer）：三角形底层任意一个节点都有可能是最小路径的终点
        // 遍历所有路径终点，返回最小值
        int best = dp[(n - 1) % 2][0];
        for (int j = 1; j < n; j++) {
            best = Math.min(best, dp[(n - 1) % 2][j]);
        }
        return best;
    }

    public static void main(String[] args) {
        int[][] triangle = {{2}, {5, 6}, {11, 10, 13}, {15, 11, 18, 16}};
        System.out.println(minimumTotal(triangle));
    }
}
