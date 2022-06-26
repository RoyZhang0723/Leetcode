/**
 *
 * https://leetcode.cn/problems/diameter-of-binary-tree/
 * 二叉树直径
 */

public class DiameterOfBinaryTree {
    static int maxDiameter = 0;
    public static int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return maxDiameter;
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        /**
         *
         * 利用后序遍历
         */
        int myDiameter = leftMax + rightMax;
        maxDiameter = Math.max(maxDiameter, myDiameter);

        return 1+Math.max(leftMax, rightMax);
    }
}
