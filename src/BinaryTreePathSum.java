import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 */
public class BinaryTreePathSum {
    public static List<List<Integer>> binaryTreePathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        int[] path = new int[maxDepth(root)];
        if (root != null) {
            pathSum(res, path, root, sum, 0);
        }
        return res;
    }

    //idx记录的是当前递归的深度，即当前访问的是二叉树的第几层
    public static void pathSum(List<List<Integer>> res, int[] curPath, TreeNode root, int sum, int idx) {
        //叶子结点，如果值和sum相等，则说明该路径是为一条目标路径，把该路径添加至List中
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                curPath[idx] = root.val;
                List<Integer> al = new ArrayList<>(idx + 1);
                for (int i = 0; i <= idx; i++) {
                    al.add(curPath[i]);
                }
                res.add(al);
                return;
            }
        }
        curPath[idx] = root.val;
        if (root.left != null) {
            pathSum(res, curPath, root.left, sum - root.val, idx + 1);
        }
        if (root.right != null) {
            pathSum(res, curPath, root.right, sum - root.val, idx + 1);
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.right == null && root.left == null)
            return 1;
        if (root.right == null)
            return maxDepth(root.left) + 1;
        if (root.left == null)
            return maxDepth(root.right) + 1;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
