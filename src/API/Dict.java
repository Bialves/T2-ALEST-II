package src.API;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        System.out.println("V: " + w.getV() + " -> W: " + w.getW());
        if (visited.containsKey(w.getW())) {
            BigInteger weight = visited.get(w.getW()).multiply(BigInteger.valueOf((int) w.getWeight()));
        
            // Adiciona a orig
            if (!visited.containsKey(w.getV())) {
                visited.put(w.getV(), weight);
                //System.out.println("> V_1: " + w.getV());
            }
            return weight;
        } else {
            // Acessa os filhos
            Iterator<Edge> adj = graph.getAdj(w.getW()).iterator();
            BigInteger weight = BigInteger.valueOf((int) w.getWeight());

            //System.out.println("> V_2: " + w.getV());
            while (adj.hasNext()) {
                // Multiplica pelo produto dos pesos dos vértices adjacentes
                weight = weight.multiply(weight(adj.next(), graph));
            }

            visited.put(w.getV(), weight);
            return weight;    
        }
    }

    @Override
    public String toString() {
        return visited.toString();
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
