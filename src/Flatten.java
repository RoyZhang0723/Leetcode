/**
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/submissions/
 * 二叉树展开为链表
 *
 */
class Flatten {
    /**
     * 利用后序位置对左子树和右子树操作
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode temp1 = root.left;
        TreeNode temp2 = root.right;
        root.left = null;
        root.right = temp1;
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = temp2;
    }
}