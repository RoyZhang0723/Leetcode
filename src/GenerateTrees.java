import java.util.ArrayList;
import java.util.List;

/**
 * https://www.lintcode.com/problem/164/
 *
 * 不同的二叉查找树II （整体法的运用）
 */
public class GenerateTrees {
    /**
     * @paramn n: An integer
     * @return: A list of root
     */
    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }

    /**
     * 返回值是从start到end到所有可能到二叉查找树
     *
     * @param start
     * @param end
     * @return
     */
    public List<TreeNode> helper(int start, int end) {
        List<TreeNode> result = new ArrayList<>();

        // 递归出口
        if (start > end) {
            result.add(null);
            return result;
        }

        // 枚举根结点的值
        for (int rootVal = start; rootVal <= end; rootVal++) {
            // 递归获得所有可能的左子树
            List<TreeNode> leftTrees = helper(start, rootVal - 1);
            // 递归获得所有可能的右子树
            List<TreeNode> rightTrees = helper(rootVal + 1, end);
            // 枚举每一种左右子树的组合，组成新的二叉树
            for (int i = 0; i < leftTrees.size(); i++) {
                TreeNode leftTree = leftTrees.get(i);
                for (int j = 0; j < rightTrees.size(); j++) {
                    TreeNode rightTree = rightTrees.get(j);
                    TreeNode root = new TreeNode(rootVal);
                    root.left = leftTree;
                    root.right = rightTree;
                    result.add(root);
                }
            }
        }
        return result;
    }
}
