package src;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import src.API.Edge;
import src.API.EdgeWeightedGraph;

public class Dict {
    private Map<String, BigInteger> visited;
    private final String INIT = "hidrogenio";
    private final String TARGET = "hidrogenio";
    private BigInteger sum;

    public Dict(EdgeWeightedGraph g) {
        this.visited = new HashMap<>();
        sum = BigInteger.ZERO;

        dfs(g, INIT);
        System.out.println("Soma total: " + sum);
    }

    private void dfs(EdgeWeightedGraph g, String v) {
        visited.put(TARGET, BigInteger.ONE);

        for (Edge w : g.getAdj(v)) {
            sum = sum.add(weight(w, g.getAdj(w.getW()).iterator()));
        }
    }

    private BigInteger weight(Edge w, Iterator<Edge> adj) {
        if (visited.containsKey(w.getW())) {
            return visited.get(w.getW()).multiply(BigInteger.valueOf((int) w.getWeight()));
        } else {
            // Se não há elementos na lista
            if (!adj.hasNext()) {
                return BigInteger.ONE;
            } else {
                BigInteger weight = BigInteger.valueOf((int) w.getWeight()).multiply(weight(adj.next(), adj));
                visited.put(w.getW(), weight);
                return weight;
            }
        }
    }

    // NOTAS

    // calcularCaminhos(hidrogenio){
    //     for filhos do hidrogenio:
    //         path(filho)
    // }

    // path (elemento){
    //     if(elemento is in visited){
    //         return visited[elemento].custo
    //     }else{
    //         custo = path(elemento.filho)
    //         visited.add(emelento, custo)
    //         return custo'
    //     }
    // }
    
}
