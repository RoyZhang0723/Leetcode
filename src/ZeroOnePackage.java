/**
 *
 * 01背包问题
 *
 *
 * 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
 * <p>
 * 第 i 件物品的体积是 vi，价值是 wi。
 * <p>
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 * 输出最大价值。
 */

public class ZeroOnePackage {
    public static int zeroOnePackage(int[][] list) {
        int N = list[0][0];
        int V = list[0][1];
        int[][] dp = new int[N + 1][V + 1];
        dp[0][0] = 0;
        /**
         *
         * 第一层循环中的i表示的是物品的序号（第几个物品前）
         * 第二层循环中的j表示的是容量
         * dp[i][j]表示的是只选择前i个物品，背包容量为j的情况下，背包中物品的最大价值
         */
        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < V + 1; j++) {
                if (j < list[i][0]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - list[i][0]] + list[i][1]);
                }
            }
        }
        return dp[N][V];
    }

    /**
     * 对上面的方法的空间复杂度进行优化
     *
     * @param list
     * @return
     */
    public static int zeroOnePackage1(int[][] list) {
        int N = list[0][0];
        int V = list[0][1];
        int[] dp = new int[V + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            /**
             * V的循环顺序要注意，最后的一个V是较小的V推出来的
             * (这样可以保证)每个物品只放一次
             */
            for (int j = V; j > 0; j--) {
                if (j < list[i][0]) {
                    continue;
                } else {
                    dp[j] = Math.max(dp[j], dp[j-list[i][0]] + list[i][1]);
                }
            }
        }
        return dp[V];
    }

    public static void main(String[] args) {
        int[][] packList = new int[5][2];
        packList[0][0] = 4;
        packList[0][1] = 5;
        packList[1][0] = 1;
        packList[1][1] = 2;
        packList[2][0] = 2;
        packList[2][1] = 4;
        packList[3][0] = 3;
        packList[3][1] = 4;
        packList[4][0] = 4;
        packList[4][1] = 5;
        System.out.println(zeroOnePackage1(packList));
        System.out.println(zeroOnePackage(packList));
    }

    public static int max(int a, int b, int c) {
        if (a > b && a > c) {
            return a;
        } else if (b > a && b > c){
            return b;
        } else {
            return c;
        }
    }
}
