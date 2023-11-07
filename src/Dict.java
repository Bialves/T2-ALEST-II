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
    private final String TARGET = "ouro";
    private BigInteger sum;

    public Dict(EdgeWeightedGraph graph) {
        this.visited = new HashMap<>();
        sum = BigInteger.ZERO;

        dfs(graph, INIT);
        System.out.println("Soma total: " + sum);
    }

    private void dfs(EdgeWeightedGraph graph, String v) {
        visited.put(TARGET, BigInteger.ONE);

        for (Edge w : graph.getAdj(v)) {
            sum = sum.add(weight(w, graph));
        }
    }

    private BigInteger weight(Edge w, EdgeWeightedGraph graph) {
        if (visited.containsKey(w.getW())) {
            return visited.get(w.getW()).multiply(BigInteger.valueOf((int) w.getWeight()));
        } else {
            // Acessa os filhos
            Iterator<Edge> adj = graph.getAdj(w.getW()).iterator();
            if (adj.hasNext()) {
                BigInteger weight = BigInteger.valueOf((int) w.getWeight()).multiply(weight(adj.next(), graph));
                visited.put(w.getV(), weight);
                return weight;
            } else {
                return BigInteger.ONE;
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
