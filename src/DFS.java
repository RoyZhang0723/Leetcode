public class DFS {
    public boolean[] marked;

    public DFS(UnDirectGraph unDirectGraph, int s) {
        this.marked = new boolean[unDirectGraph.V];
        dfs(unDirectGraph, s);
    }

    public void dfs(UnDirectGraph unDirectGraph, int s) {
        marked[s] = true;
        for (Integer v : unDirectGraph.adj(s)) {
            if (!marked[v]) {
                dfs(unDirectGraph, v);
            }
        }
    }

    public boolean hasPath(int e){
        return marked[e];
    }


    public static void main(String[] args) {
        UnDirectGraph unDirectGraph = new UnDirectGraph(6);
//        unDirectGraph.addEdge(0, 1);
        unDirectGraph.addEdge(0, 2);
        unDirectGraph.addEdge(0, 3);
        unDirectGraph.addEdge(1, 4);
        unDirectGraph.addEdge(2, 4);
        unDirectGraph.addEdge(2, 5);
        unDirectGraph.addEdge(2, 3);
        unDirectGraph.addEdge(3, 5);
        unDirectGraph.addEdge(4, 5);
        DFS dfs = new DFS(unDirectGraph, 0);
        System.out.println(dfs.hasPath(4));
    }
}
