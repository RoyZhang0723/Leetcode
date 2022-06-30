/**
 *
 * https://leetcode.cn/problems/binary-search-tree-to-greater-sum-tree/
 * 从二叉搜索树到更大和树
 *
 */

public class BstToGst {
    /**
     * 二叉搜索树的中序遍历结果为升序的排序数组
     * @param root
     * @return
     */
    public TreeNode bstToGst(TreeNode root) {
        traverse(root);
        return root;
    }

    int sum = 0;
    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.right);
        sum += root.val;
        root.val = sum;
        traverse(root.left);
    }
}
