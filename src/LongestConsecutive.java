/**
 *  二叉树最长连续序列（其实得是二叉查找树）dfs
 *  https://www.lintcode.com/problem/595/
 */
public class LongestConsecutive {
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public static int longestConsecutive(TreeNode root) {
        int[] result = helper(root);
        return result[1];
    }

    /**
     * 返回以 root 为端点的最长链，和以 root 为根的子树的最长链
     * @param root
     * @return
     */
    private static int[] helper(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int rootMaxLength = 1;
        int subTreeMaxLength = 1;

        // 处理左子树的信息
        int[] leftResult = helper(root.left);
        // 如果左子树能连上，则路径长度加1
        if (root.left != null && root.val + 1 == root.left.val) {
            rootMaxLength = Math.max(rootMaxLength, leftResult[0] + 1);
        }
        subTreeMaxLength = Math.max(subTreeMaxLength, leftResult[1]);

        // 处理右子树的信息
        int[] rightResult = helper(root.right);
        // 如果右子树能连上，则路径长度加1
        if (root.right != null && root.val + 1 == root.right.val) {
            rootMaxLength = Math.max(rootMaxLength, rightResult[0] + 1);
        }
        subTreeMaxLength = Math.max(subTreeMaxLength, rightResult[1]);

        // 考虑当前节点为端点的链是子树最长链的情况
        subTreeMaxLength = Math.max(subTreeMaxLength, rootMaxLength);

        int[] result = new int[2];
        result[0] = rootMaxLength;
        result[1] = subTreeMaxLength;
        return result;
    }
}
