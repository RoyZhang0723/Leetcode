/**
 * https://leetcode.cn/problems/number-of-music-playlists/solutions/24767/bo-fang-lie-biao-de-shu-liang-by-leetcode/?envType=study-plan-v2&envId=dynamic-programming-grandmaster
 * 播放列表的数量
 * 动态规划好题
 */
public class NumMusicPlaylists {
    public int numMusicPlaylists(int N, int L, int K) {
        int MOD = 1_000_000_007;

        //dp[i][j]表示播放列表长度为i，包含恰好j首歌曲的数量
        long[][] dp = new long[L+1][N+1];
        dp[0][0] = 1;
        for (int i = 1; i <= L; ++i)
            for (int j = 1; j <= N; ++j) {
                //如果最后一个播放的是之前没有播放的话，说明之前播放过j-1首曲子，而播放列表长度为i-1，这样就和之前的表达式形成递推
                dp[i][j] += dp[i-1][j-1] * (N-j+1);
                //如果最后一个播放的是之前放过的，那么说明之前播放过j首曲子，而播放列表长度为i-1，因为只能重复播放k首歌之前的，所有乘以j-k
                dp[i][j] += dp[i-1][j] * Math.max(j-K, 0);
                dp[i][j] %= MOD;
            }

        return (int) dp[L][N];
    }
}
