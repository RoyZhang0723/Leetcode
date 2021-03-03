public class CountNodes {
    static int leavesNum = 0;

    /**
     * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
     *
     * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param treeNode
     * @return
     */
    public static int countNodes(TreeNode treeNode) {
        int count = 1;
        TreeNode root = treeNode;
        int height = 0;
        while (treeNode != null) {
            height++;
            treeNode = treeNode.left;
        }
        height--;
        for (int i = 0; i < height; i++) {
            count = count * 2;
        }
        count--;
        countLeaves(root, 1, ++height);
        count = count + leavesNum;
        return count;
    }


    public static void countLeaves(TreeNode treeNode, int h, int last) {
        if (treeNode == null) {
            return;
        } else if (treeNode.left == null && treeNode.right == null) {
            if (h == last) {
                leavesNum++;
            }
        } else if (treeNode.left == null) {
            countLeaves(treeNode.right, ++h, last);
        } else if (treeNode.right == null) {
            countLeaves(treeNode.left, ++h, last);
        } else {
            countLeaves(treeNode.left, ++h, last);
            countLeaves(treeNode.right, h, last);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
//        TreeNode treeNode5 = new TreeNode(5);
//        TreeNode treeNode6 = new TreeNode(6);
//        TreeNode treeNode7 = new TreeNode(7);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
//        treeNode2.right = treeNode5;
//        treeNode3.left = treeNode6;
//        treeNode3.right = treeNode7;
        System.out.println(countNodes(treeNode1));
    }

}
