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
        BigDecimal weight = new BigDecimal(1);

        if (g.getAdj(v) == null) {
            v = edgeTo.get(v);
        }

        // Marquei v (root)
        marked.add(v);
        // Descubro os vizinhos de v
        for (Edge w : g.getAdj(v)) {
            System.out.println(v + " | " + w.getV() + " " + w.getW() + " " + w.getWeight());
            // Se o vizinho não foi marcado, marco e chamo recursivamente
            if (!marked.contains(w.getW())) {
                edgeTo.put(w.getW(), v);
                sum = weight.multiply(new BigDecimal(w.getWeight()));
                weight = sum;
                dfs(g, w.getW());
            }
        }
    }

    public BigDecimal getWeight() {
        return sum;
    }
}