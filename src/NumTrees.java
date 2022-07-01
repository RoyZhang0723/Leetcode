/**
 *
 * https://leetcode.cn/problems/unique-binary-search-trees/
 * 不同的二叉搜索树
 */

public class NumTrees {
    public static int numTrees(int n) {
        return count(1, n);
    }

    public static int count(int lo, int hi) {
        if (lo > hi) return 1;

        int res = 0;
        for (int i = lo; i <= hi; i++) {
            // i 的值作为根节点root
            int left = count(lo, i - 1);
            int right = count(i + 1, hi);
            // 左右子树的组合数乘积是BST的总数
            res += left * right;
        }

        return res;
    }

    /**
     * 利用备忘录（动态规划）来处理重叠子问题
     * @param n
     * @return
     */
    static int[][] memo;
    public static int numTrees1(int n) {
        memo = new int[n + 1][n + 1];
        return count1(1, n);
    }

    public static int count1(int lo, int hi) {
        if (lo > hi) return 1;
        // check memo
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }

        int res = 0;
        for (int mid = lo; mid <= hi; mid++) {
            int left = count(lo, mid - 1);
            int right = count(mid + 1, hi);
            res += left * right;
        }
        // store the result to memo
        memo[lo][hi] = res;

        return res;
    }
}
