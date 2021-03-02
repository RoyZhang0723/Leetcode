import sun.misc.Queue;

public class TreeTraverse {
    static Queue<BinaryTreeNode> binaryTreeNodeQueue = new Queue<>();

    public static void main(String[] args) throws InterruptedException {
        /**
         * root (binaryTreeNode1)
         */
        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode binaryTreeNode2 = new BinaryTreeNode(2);
        BinaryTreeNode binaryTreeNode3 = new BinaryTreeNode(3);
        BinaryTreeNode binaryTreeNode4 = new BinaryTreeNode(4);
        BinaryTreeNode binaryTreeNode5 = new BinaryTreeNode(5);
        BinaryTreeNode binaryTreeNode6 = new BinaryTreeNode(6);
        BinaryTreeNode binaryTreeNode7 = new BinaryTreeNode(7);
        root.leftNode = binaryTreeNode2;
        root.rightNode = binaryTreeNode3;
        binaryTreeNode2.leftNode = binaryTreeNode4;
        binaryTreeNode2.rightNode = binaryTreeNode5;
        binaryTreeNode3.leftNode = binaryTreeNode6;
        binaryTreeNode3.rightNode = binaryTreeNode7;
        levelOrderTraverse(root);
        System.out.println("\n");
        firstOrderTraverse(root);
        System.out.println("\n");
        inOrderTraverse(root);
        System.out.println("\n");
        lastOrderTraverse(root);
    }

    /**
     * 二叉树的层序遍历
     *
     * @param binaryTreeNode
     * @throws InterruptedException
     */
    public static void levelOrderTraverse(BinaryTreeNode binaryTreeNode) throws InterruptedException {
        BinaryTreeNode outNode;
        if (binaryTreeNode != null) {
            binaryTreeNodeQueue.enqueue(binaryTreeNode);
        }
        while (!binaryTreeNodeQueue.isEmpty()) {
            outNode = binaryTreeNodeQueue.dequeue();
            System.out.println(outNode.value);
            if (outNode.leftNode == null && outNode.rightNode == null) {

            } else if (outNode.leftNode == null) {
                binaryTreeNodeQueue.enqueue(outNode.rightNode);
            } else if (outNode.rightNode == null) {
                binaryTreeNodeQueue.enqueue(outNode.leftNode);
            } else {
                binaryTreeNodeQueue.enqueue(outNode.leftNode);
                binaryTreeNodeQueue.enqueue(outNode.rightNode);
            }
        }
    }

    /**
     * 先序遍历
     * @param binaryTreeNode
     */
    public static void firstOrderTraverse(BinaryTreeNode binaryTreeNode) {
        System.out.println(binaryTreeNode.value);
        if (binaryTreeNode.rightNode == null && binaryTreeNode.leftNode == null) {
        } else if (binaryTreeNode.leftNode == null) {
            firstOrderTraverse(binaryTreeNode.rightNode);
        } else if (binaryTreeNode.rightNode == null) {
            firstOrderTraverse(binaryTreeNode.leftNode);
        } else {
            firstOrderTraverse(binaryTreeNode.leftNode);
            firstOrderTraverse(binaryTreeNode.rightNode);
        }
    }

    /**
     * 中序遍历
     * @param binaryTreeNode
     */
    public static void inOrderTraverse(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode.rightNode == null && binaryTreeNode.leftNode == null) {
            System.out.println(binaryTreeNode.value);
        } else if (binaryTreeNode.leftNode == null) {
            System.out.println(binaryTreeNode.value);
            inOrderTraverse(binaryTreeNode.rightNode);
        } else if (binaryTreeNode.rightNode == null) {
            inOrderTraverse(binaryTreeNode.leftNode);
            System.out.println(binaryTreeNode.value);
        } else {
            inOrderTraverse(binaryTreeNode.leftNode);
            System.out.println(binaryTreeNode.value);
            inOrderTraverse(binaryTreeNode.rightNode);
        }
    }

    /**
     * 后序遍历
     * @param binaryTreeNode
     */
    public static void lastOrderTraverse(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode.rightNode == null && binaryTreeNode.leftNode == null) {
            System.out.println(binaryTreeNode.value);
        } else if (binaryTreeNode.leftNode == null) {
            lastOrderTraverse(binaryTreeNode.rightNode);
            System.out.println(binaryTreeNode.value);
        } else if (binaryTreeNode.rightNode == null) {
            lastOrderTraverse(binaryTreeNode.leftNode);
            System.out.println(binaryTreeNode.value);
        } else{
            lastOrderTraverse(binaryTreeNode.leftNode);
            lastOrderTraverse(binaryTreeNode.rightNode);
            System.out.println(binaryTreeNode.value);
        }
    }
}
