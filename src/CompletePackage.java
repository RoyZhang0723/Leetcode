/**
 * 完全背包问题
 */
public class CompletePackage {
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
        System.out.println(completePackage(packList));
    }

    public static int completePackage(int[][] list) {
        int N = list[0][0];
        int V = list[0][1];
        int[] dp = new int[V + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            /**
             * 完全背包问题和01背包问题在代码上核心的区别就是在V的循环顺序上面
             * 从0开始到V可以
             */
            for (int j = 0; j <= V; j++) {
                if (j < list[i][0]) {
                    continue;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - list[i][0]] + list[i][1]);
                }
            }
        }
        return dp[V];
    }
}
