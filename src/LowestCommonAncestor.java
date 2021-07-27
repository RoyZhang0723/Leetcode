/**
 *
 * 最近的公共祖先（理解分治法特别好的题目）
 * https://www.lintcode.com/problem/88
 */
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        if (root == null) {
            return null;
        }
        // 如果root为A或B，立即返回，无需继续向下寻找
        if (root == A || root == B) {
            return root;
        }
        /**
         * 关键就在这一步，要想到把左边和右边当成两个整体，然后让程序递归去求解，直接取答案就可
         */
        TreeNode left = lowestCommonAncestor(root.left, A, B);
        TreeNode right = lowestCommonAncestor(root.right, A, B);

        // 如果A，B分别存在于两棵子树，root为LCA，返回root
        if (left != null && right != null) {
            return root;
        }
        // 左子树有一个点或者左子树有LCA
        if (left != null) {
            return left;
        }
        // 右子树有一个点或者右子树有LCA
        if (right != null) {
            return right;
        }
        // 左右子树啥都没有
        return null;
    }
}
