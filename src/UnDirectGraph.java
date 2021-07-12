import java.util.Collections;

public class UnDirectGraph extends Graph{

    UnDirectGraph(int V) {
        super(V);
    }

    @Override
    void addEdge(int v, int w) {
        this.adj[v].add(w);
        this.adj[w].add(v);
        this.E++;
    }

    @Override
    Iterable<Integer> adj(int v) {
        return this.adj[v];
    }

    @Override
    boolean hasPathTo(int s, int v) {
        return false;
    }

    @Override
    Iterable<Integer> pathTo(int s, int v) {
        return null;
    }
}
