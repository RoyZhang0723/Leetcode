public class IsSubStructure {
    /**
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * <p>
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     * <p>
     * leetcode链接：
     * https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
     */
    public static void main(String[] args) {
        /**
         * test case 1
         */
//        TreeNode treeNodeA1 = new TreeNode(1);
//        TreeNode treeNodeA2 = new TreeNode(2);
//        TreeNode treeNodeA3 = new TreeNode(3);
//        treeNodeA1.left = treeNodeA2;
//        treeNodeA1.right = treeNodeA3;
//        TreeNode treeNodeB1 = new TreeNode(3);
//        TreeNode treeNodeB2 = new TreeNode(1);
//        treeNodeB1.left = treeNodeB2;

        /**
         * test case 2
         */
        TreeNode treeNodeA1 = new TreeNode(3);
        TreeNode treeNodeA2 = new TreeNode(4);
        TreeNode treeNodeA3 = new TreeNode(5);
        TreeNode treeNodeA4 = new TreeNode(1);
        TreeNode treeNodeA5 = new TreeNode(2);
        treeNodeA1.left = treeNodeA2;
        treeNodeA1.right = treeNodeA3;
        treeNodeA2.left = treeNodeA4;
        treeNodeA2.right = treeNodeA5;
        TreeNode treeNodeB1 = new TreeNode(4);
        TreeNode treeNodeB2 = new TreeNode(1);
        treeNodeB1.left = treeNodeB2;
        System.out.println(isSubStructure(treeNodeA1, treeNodeB1));


    }

    /**
     * 每个节点用recur来递归比较
     * @param A
     * @param B
     * @return
     */
    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        if (recur(A, B)) {
            return true;
        } else {
            if (A.left == null && A.right == null) {
                return recur(A, B);
            } else if (A.left == null) {
                return isSubStructure(A.right, B);
            } else if (A.right == null) {
                return isSubStructure(A.left, B);
            } else {
                return isSubStructure(A.left, B) || isSubStructure(A.right, B);
            }
        }
    }

    /**
     * （核心函数）
     * 比较逻辑:
     * 1.如果子结构B比较到了null，说明过程走完了，返回true
     * 2.如何A比较到null了，说明父结构里面没有包含到B，返回false
     * 3.每次比较相应节点的值
     * @param A
     * @param B
     * @return
     */
    public static boolean recur(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        } else if (A == null) {
            return false;
        } else {
            return (A.val == B.val) && recur(A.left, B.left) && recur(A.right, B.right);
        }
    }
}
