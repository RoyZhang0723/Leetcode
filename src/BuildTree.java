/**
 *
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/submissions/
 *
 * 从前序与中序遍历序列构造二叉树
 */

public class BuildTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode build(int[] preorder, int[] inorder, int preLo, int preHi, int inLo, int inHi) {
        if (preLo > preHi) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preLo]);
        if (preLo == preHi) {
            return root;
        }
        int index = 0, len;
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[preLo] == inorder[i]) {
                index = i;
                break;
            }
        }
        len = index - inLo;
        root.left = build(preorder, inorder, preLo + 1, preLo + len, inLo, index - 1);
        root.right = build(preorder, inorder, preLo + 1 + len, preHi, index + 1, inHi);
        return root;
    }
}
