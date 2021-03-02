import sun.misc.Queue;

import java.util.HashMap;
import java.util.Map;

public class PruneTree {
    /**
     * 给定二叉树根结点root，此外树的每个结点的值要么是 0，要么是 1。
     * <p>
     * 返回移除了所有不包含 1 的子树的原二叉树。
     * <p>
     * ( 节点 X 的子树为 X 本身，以及所有 X 的后代。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-pruning
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static Map<BinaryTreeNode, BinaryTreeNode> parent = new HashMap();

    public static void main(String[] args) throws InterruptedException {
        BinaryTreeNode binaryTreeNode1 = new BinaryTreeNode(1);
        BinaryTreeNode binaryTreeNode2 = new BinaryTreeNode(0);
        BinaryTreeNode binaryTreeNode3 = new BinaryTreeNode(0);
        BinaryTreeNode binaryTreeNode4 = new BinaryTreeNode(1);
        binaryTreeNode1.rightNode = binaryTreeNode2;
        binaryTreeNode2.leftNode = binaryTreeNode3;
        binaryTreeNode2.rightNode = binaryTreeNode4;
        binaryTreeNode1 = pruneTree(binaryTreeNode1);
        firstOrderTraverse(binaryTreeNode1);
    }

    /**
     *
     * 我的做法
     * @param root
     * @return
     * @throws InterruptedException
     */
    public static BinaryTreeNode pruneTree(BinaryTreeNode root) throws InterruptedException {
        Queue<BinaryTreeNode> binaryTreeNodeQueue = new Queue<>();
        BinaryTreeNode outNode;
        BinaryTreeNode par;
        binaryTreeNodeQueue.enqueue(root);
        dfs(root, null);
        while (!binaryTreeNodeQueue.isEmpty()) {
            outNode = binaryTreeNodeQueue.dequeue();
            if (isNoneOneTree(outNode)) {
                par = parent.get(outNode);
                if (par.leftNode==outNode) {
                    par.leftNode = null;
                } else {
                    par.rightNode = null;
                }
            } else {
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
        return root;
    }

    public static void dfs(BinaryTreeNode node, BinaryTreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.leftNode, node);
            dfs(node.rightNode, node);
        }
    }

    public static boolean isNoneOneTree(BinaryTreeNode root) {
        if (root.value == 1) {
            return false;
        } else if (root.leftNode == null && root.rightNode == null) {
            return true;
        } else if (root.leftNode == null) {
            return isNoneOneTree(root.rightNode);
        } else if (root.rightNode == null) {
            return isNoneOneTree(root.leftNode);
        } else {
            return isNoneOneTree(root.leftNode) && isNoneOneTree(root.rightNode);
        }
    }

    public static void firstOrderTraverse(BinaryTreeNode root) {
        if (root == null) {
            return;
        } else if (root.leftNode == null && root.rightNode == null) {
            System.out.println(root.value);
        } else if (root.leftNode == null) {
            System.out.println(root.value);
            firstOrderTraverse(root.rightNode);
        } else if (root.rightNode == null) {
            System.out.println(root.value);
            firstOrderTraverse(root.leftNode);
        } else {
            System.out.println(root.value);
            firstOrderTraverse(root.leftNode);
            firstOrderTraverse(root.rightNode);
        }
    }

    /**
     * 答案的做法（更加简洁）
     * 递归查找修改数，无需层序遍历来找，碰到不包含1就把链接的结点改为null
     * @param root
     * @return
     */
    public BinaryTreeNode pruneTree1(BinaryTreeNode root) {
        return containsOne(root) ? root : null;
    }

    public boolean containsOne(BinaryTreeNode node) {
        if (node == null) return false;
        boolean a1 = containsOne(node.leftNode);
        boolean a2 = containsOne(node.rightNode);
        if (!a1) node.leftNode = null;
        if (!a2) node.rightNode = null;
        return node.value == 1 || a1 || a2;
    }
}
