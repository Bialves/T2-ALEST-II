package src.API;

import java.math.BigInteger;
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
    private BigInteger sum;
    private Map<String, BigInteger> memoization;

    public DepthFirstSearch(EdgeWeightedGraph edgeWeightedGraph, String s) {
        this.s = s;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
        this.sum = BigInteger.valueOf(0);
        this.memoization = new HashMap<>();
        dfs(edgeWeightedGraph, s, BigInteger.valueOf(1));
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

    // alteração do método dfs utilizando memoização para evitar recalcular a
    // quantidade de hidrogênio necessária para percorrer os mesmos nodos novamente
    private void dfs(EdgeWeightedGraph g, String v, BigInteger sum_path) {
        /**
         * Se v é 'ouro', retorno para o root,
         * salvo a soma total do caminho e reseto o sum_path
         */
        if (v.equals("ouro")) {
            this.sum = this.sum.add(sum_path);
            sum_path = BigInteger.valueOf(1);
            return;
        }
        // Verifique se a memoização já possui a quantidade de hidrogênio para o nó
        // atual
        if (memoization.containsKey(v)) {
            sum_path = sum_path.multiply(memoization.get(v));
        } else {
            for (Edge w : g.getAdj(v)) {
                // Realizo a multiplicação da sum_path com o peso das arestas do caminho
                BigInteger aux = BigInteger.valueOf((int) w.getWeight());
                System.out.println(v + " -> " + w.getW() + " | sum_path: " + (sum_path.intValue() * w.getWeight()));

                // Faço a chamada recursiva para os vizinhos de v
                dfs(g, w.getW(), sum_path.multiply(aux));
            }

            // Armazene o resultado na memoização
            memoization.put(v, sum_path);
        }
    }

    public BigInteger getSum() {
        return this.sum;
    }
}