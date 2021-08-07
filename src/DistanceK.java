import java.util.*;

/**
 * 二叉树中所有距离为 K 的结点
 *
 * https://www.lintcode.com/problem/1506/
 *
 * 将树转化为图再bfs逐层找距离为K的结点
 *
 */
public class DistanceK {
    static Map<TreeNode, TreeNode> parent = new HashMap<>();
    // 注意在这里初始化的时候，要用LinkedList而不能用ArrayDeque(添加null的时候会报错)
    static Queue<TreeNode> treeNodeQueue = new LinkedList<>();
    static List<Integer> ansList = new ArrayList<>();
    /**
     * @param root: the root of the tree
     * @param target: the target
     * @param K: the given K
     * @return: All Nodes Distance K in Binary Tree
     */
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        TreeNode n;
        dfs(root, null);
        treeNodeQueue.offer(null);
        treeNodeQueue.offer(target);
        // 防止在回溯到par的时候再走回头路
        Set<TreeNode> seen = new HashSet<>();
        seen.add(target);
        seen.add(null);
        int dist = 0;
        while (!treeNodeQueue.isEmpty()) {
            TreeNode node = treeNodeQueue.poll();
            if (node == null) {
                if (dist == K) {
                    while (!treeNodeQueue.isEmpty()) {
                        n = treeNodeQueue.poll();
                        ansList.add(n.val);
                    }
                    return ansList;
                }
                // 这里的null是用来分层的
                treeNodeQueue.offer(null);
                dist++;
            } else {
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    treeNodeQueue.offer(node.left);
                }
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    treeNodeQueue.offer(node.right);
                }
                TreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    treeNodeQueue.offer(par);
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * 把每个节点的父节点记录在map里面，这样就讲树变成图了
     * @param node
     * @param par
     */
    public static void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(1);
        TreeNode treeNode4 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(2);
        TreeNode treeNode6 = new TreeNode(0);
        TreeNode treeNode7 = new TreeNode(8);
        TreeNode treeNode8 = new TreeNode(7);
        TreeNode treeNode9 = new TreeNode(4);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode5.left = treeNode8;
        treeNode5.right = treeNode9;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        List<Integer> ansList = distanceK(treeNode1, treeNode2, 2);
//        Queue<TreeNode> treeNodeQueue = new ArrayDeque<>();
//        treeNodeQueue.offer(null);
//        TreeNode a = new TreeNode(3);
//        treeNodeQueue.offer(a);
    }
}
