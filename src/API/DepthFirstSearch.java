package src.API;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DepthFirstSearch {
    private Set<String> marked;
    private Map<String, String> edgeTo;
    private String s;
    private BigDecimal sum;

    public DepthFirstSearch(EdgeWeightedGraph edgeWeightedGraph, String s) {
        this.s = s;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
        this.sum = new BigDecimal(1);
        dfs(edgeWeightedGraph, s);
    }

    public boolean hasPathTo(String v) {
        return marked.contains(v);
    }

    public Iterable<String> pathTo(String v) {
        List<String> path = new LinkedList<>();
        if (hasPathTo(v)) {
            while (!v.equals(s)) {
                path.add(0, v);
                v = edgeTo.get(v);
            }
            path.add(0, s);
        }
        return path;
    }

    private void dfs(EdgeWeightedGraph g, String v) {
        if (v.equals("ouro")) {
            return;
        }

        // Marqo v (root)
        marked.add(v);
        // Descubro os vizinhos de v
        for (Edge w : g.getAdj(v)) {
            System.out.println(v + " | Adj: " + w.getW() + " " + w.getWeight());
            // Se o vizinho não foi marcado, marco e chamo recursivamente
            edgeTo.put(w.getW(), v);
            // Somo os pesos
            BigDecimal aux = new BigDecimal(1);
            aux = aux.multiply(new BigDecimal(w.getWeight()));
            this.sum = this.sum.multiply(aux);
            System.out.println("Soma: " + this.sum);
            // Faço a chamada recursiva para os vizinhos de v
            dfs(g, w.getW());
        }
    }

    public BigDecimal getWeight() {
        return sum;
    }
}