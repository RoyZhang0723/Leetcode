import sun.misc.Queue;

import java.util.*;

public class DistanceK {
    static Map<BinaryTreeNode, BinaryTreeNode> parent = new HashMap<>();
    static Queue<BinaryTreeNode> binaryTreeNodeQueue = new Queue<>();
    static List<Integer> ansList = new ArrayList<>();

    /**
     * 二叉树中所有距离为k的结点
     * 给定一个二叉树（具有根结点root），一个目标结点target，和一个整数值 K 。
     * <p>
     * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree
     */
    public static void main(String[] args) throws InterruptedException {
        /**
         * binaryTreeNode1(root)
         */
        BinaryTreeNode binaryTreeNode1 = new BinaryTreeNode(3);
        BinaryTreeNode binaryTreeNode2 = new BinaryTreeNode(5);
        BinaryTreeNode binaryTreeNode3 = new BinaryTreeNode(1);
        BinaryTreeNode binaryTreeNode4 = new BinaryTreeNode(6);
        BinaryTreeNode binaryTreeNode5 = new BinaryTreeNode(2);
        BinaryTreeNode binaryTreeNode6 = new BinaryTreeNode(0);
        BinaryTreeNode binaryTreeNode7 = new BinaryTreeNode(8);
        BinaryTreeNode binaryTreeNode8 = new BinaryTreeNode(7);
        BinaryTreeNode binaryTreeNode9 = new BinaryTreeNode(4);
        binaryTreeNode1.leftNode = binaryTreeNode2;
        binaryTreeNode1.rightNode = binaryTreeNode3;
        binaryTreeNode2.leftNode = binaryTreeNode4;
        binaryTreeNode2.rightNode = binaryTreeNode5;
        binaryTreeNode5.leftNode = binaryTreeNode8;
        binaryTreeNode5.rightNode = binaryTreeNode9;
        binaryTreeNode3.leftNode = binaryTreeNode6;
        binaryTreeNode3.rightNode = binaryTreeNode7;
        List<Integer> ansL = distanceK(binaryTreeNode1, binaryTreeNode2, 2);
        for (Integer integer : ansL) {
            System.out.println(integer);
        }
    }

    /**
     * 先将二叉树变成图
     * 然后从target开始进行bfs（以后记住bfs就是用队列实现的，用bfs必用到队列）
     * 核心在于通过加入null的方法，对该图的层级进行分割
     * @param root
     * @param target
     * @param K
     * @return
     * @throws InterruptedException
     */
    public static List<Integer> distanceK(BinaryTreeNode root, BinaryTreeNode target, int K) throws InterruptedException {
        BinaryTreeNode n;
        dfs(root, null);
        binaryTreeNodeQueue.enqueue(null);
        binaryTreeNodeQueue.enqueue(target);
        Set<BinaryTreeNode> seen = new HashSet<>();
        seen.add(target);
        seen.add(null);
        int dist = 0;
        while (!binaryTreeNodeQueue.isEmpty()) {
            BinaryTreeNode node = binaryTreeNodeQueue.dequeue();
            if (node == null) {
                if (dist == K) {
                    while (!binaryTreeNodeQueue.isEmpty()) {
                        n = binaryTreeNodeQueue.dequeue();
                        ansList.add(n.value);
                    }
                    return ansList;
                }
                /**
                 * 这里的null就是为了把该二叉树的每一个层级分开，方便记录距离
                 */
                binaryTreeNodeQueue.enqueue(null);
                dist++;
            } else {
                if (!seen.contains(node.leftNode)) {
                    seen.add(node.leftNode);
                    binaryTreeNodeQueue.enqueue(node.leftNode);
                }
                if (!seen.contains(node.rightNode)) {
                    seen.add(node.rightNode);
                    binaryTreeNodeQueue.enqueue(node.rightNode);
                }
                BinaryTreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    binaryTreeNodeQueue.enqueue(par);
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * 该方法将所有的结点的父节点记录到map里，这样就成了双向连接，成为图了
     * @param node
     * @param par
     */
    public static void dfs(BinaryTreeNode node, BinaryTreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.leftNode, node);
            dfs(node.rightNode, node);
        }
    }
}
