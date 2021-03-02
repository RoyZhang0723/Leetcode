public class BinaryTreeNode {
    int value;
    BinaryTreeNode leftNode;
    BinaryTreeNode rightNode;
    public BinaryTreeNode(int x){
        value = x;
    }
    public BinaryTreeNode(){ }
    public BinaryTreeNode(BinaryTreeNode left, BinaryTreeNode right, int x) {
        leftNode = left;
        rightNode = right;
        value = x;
    }
}
