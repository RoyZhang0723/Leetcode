import java.util.*;

/**
 *
 * 拓扑排序
 * https://www.lintcode.com/problem/127/solution/16685
 */
public class TopSort {
    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // 1.统计每个点的入度
        Map<DirectedGraphNode, Integer> indegree = getInDegree(graph);

        // 2.将每个入度为0的点放入队列（Queue）中作为起始节点
        Queue<DirectedGraphNode> queue = new ArrayDeque<>();
        for (DirectedGraphNode node : graph) {
            if (!indegree.containsKey(node)) {
                queue.offer(node);
            }
        }

        // 记录拓扑顺序
        ArrayList<DirectedGraphNode> order = new ArrayList<>();

        // 3.不断从队列中拿出一个点，去掉这个点的所有连边（指向其他点的边），其他点的相应的入度 -1
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            order.add(node);
            for (DirectedGraphNode neighbor : node.neighbors) {
                // 当前点的所有邻居的入度减1
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                // 4. 一旦发现新的入度为0的点，丢回队列中
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return order;
    }

    private Map<DirectedGraphNode, Integer> getInDegree(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Integer> inDegree = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
            }
        }
        return inDegree;
    }
}
