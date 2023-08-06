import java.util.Arrays;

/**
 *
 * 实际上，对于一个长度为 NNN 的数组，其中没有出现的最小正整数只能在 [1,N+1][1, N+1][1,N+1] 中。这是因为如果 [1,N][1, N][1,N] 都出现了，
 * 那么答案是 N+1N+1N+1，否则答案是 [1,N][1, N][1,N] 中没有出现的最小正整数。这样一来，我们将所有在 [1,N][1, N][1,N] 范围内的数放入哈希表，
 * 也可以得到最终的答案。而给定的数组恰好长度为 NNN，这让我们有了一种将数组设计成哈希表的思路：
 *
 * 我们对数组进行遍历，对于遍历到的数 xxx，如果它在 [1,N][1, N][1,N] 的范围内，那么就将数组中的第 x−1x-1x−1 个位置（注意：数组下标从 000 开始）打上「标记」。
 * 在遍历结束之后，如果所有的位置都被打上了标记，那么答案是 N+1N+1N+1，否则答案是最小的没有打上标记的位置加 111。
 *
 * https://leetcode.cn/problems/first-missing-positive/solutions/304743/que-shi-de-di-yi-ge-zheng-shu-by-leetcode-solution/?envType=study-plan-v2&envId=bytedance-2023-spring-sprint
 * 缺失的第一个正数
 *
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int[] list = new int[n];
        Arrays.fill(list, 1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 2;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] < n ) {
                list[nums[i] - 1] = -1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
