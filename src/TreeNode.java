public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x){
        val = x;
    }
    public TreeNode(){ }
    public TreeNode(TreeNode leftNode, TreeNode rightNode, int x) {
        left = leftNode;
        right = rightNode;
        val = x;
    }
}
