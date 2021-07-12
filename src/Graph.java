import java.util.List;
import java.util.ArrayList;

public abstract class Graph {
    /**
     * 顶点数量
     */
    int V;
    /**
     * 边的数量
     */
    int E;
    /**
     * 邻接表
     */
    List[] adj;

    Graph(int V) {
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        this.V = V;
    }

    int V() {
        return V;
    }

    int E() {
        return E;
    }

    abstract void addEdge(int v, int w);

    abstract Iterable<Integer> adj(int v);

    abstract boolean hasPathTo(int s, int v);

    abstract Iterable<Integer> pathTo(int s, int v);


    @Override
    public String toString() {
        String s = "Graph{" + "V=" + V + ", E=" + E + '}';
        s += "\n";
        for (int v = 0; v < V; v++) {
            s += (v + ":");
            for (Integer w : this.adj(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }

//    public static void main(String[] args) {
//        Graph graph = new Graph(3);
//        graph.addEdge(0,1);
//        graph.addEdge(2,0);
//        System.out.println(graph.toString());
//    }
}
