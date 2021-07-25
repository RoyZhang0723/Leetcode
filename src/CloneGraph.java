import java.util.*;

/**
 * 克隆图
 */
public class CloneGraph {
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // write your code here
        if (node == null) {
            return null;
        }
        List<UndirectedGraphNode> nodes = findNodesByBFS(node);
        Map<UndirectedGraphNode, UndirectedGraphNode> mapping = copyNodes(nodes);
        copyEdges(nodes, mapping);

        return mapping.get(node);
    }

    private List<UndirectedGraphNode> findNodesByBFS(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        // 用于保存所有点，不重不漏
        Set<UndirectedGraphNode> visited = new HashSet<>();
        queue.offer(node);
        visited.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode curNode = queue.poll();
            for (UndirectedGraphNode neighbor: curNode.neighbors) {
                // 如果之前已经找到了这个点，无需再次BFS，否则死循环
                if (visited.contains(neighbor)) {
                    continue;
                }
                visited.add(neighbor);
                queue.offer(neighbor);
            }
        }
        return new LinkedList<>(visited);
    }

    //第二步：复制所有点
    private Map<UndirectedGraphNode, UndirectedGraphNode> copyNodes(List<UndirectedGraphNode> nodes) {
        Map<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();
        for (UndirectedGraphNode node: nodes) {
            mapping.put(node, new UndirectedGraphNode(node.label));
        }
        return mapping;
    }

    //第三步：复制所有点
    private void copyEdges(List<UndirectedGraphNode> nodes, Map<UndirectedGraphNode, UndirectedGraphNode> mapping) {
        for (UndirectedGraphNode node: nodes) {
            UndirectedGraphNode newNode = mapping.get(node);
            //旧点有哪些旧邻居，新点就有哪些新邻居
            for (UndirectedGraphNode neighbor: node.neighbors) {
                UndirectedGraphNode newNeighbor = mapping.get(neighbor);
                newNode.neighbors.add(newNeighbor);
            }
        }
    }
}
