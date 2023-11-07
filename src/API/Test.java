package src.API;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Test {
    private final String INIT = "hidrogenio";
    private final String TARGET = "ouro";
    private BigInteger sum = BigInteger.ZERO;
    private Set<String> visited;
    private Map<String, Stack<Edge>> pathsVisited;

    public Test(EdgeWeightedGraph g) {
        visited = new HashSet<>();
        pathsVisited = new HashMap<>();
    
        dfs(g);
        System.out.println("Soma total: " + sum);
    }

    private void dfs(EdgeWeightedGraph g) {
        pathsVisited.put(INIT, new Stack<>());

        dfs(g, INIT, BigInteger.ONE, pathsVisited);
    }

    private void dfs(EdgeWeightedGraph graph, String v, BigInteger sum_path, Map<String, Stack<Edge>> pathsVisited) {
        if (v.equals(TARGET)) {
            pathsVisited.get();
            this.sum = this.sum.add(sum_path);
            return;
        }

        // Marco os visitados
        visited.add(v);

        // Visito os adjacentes
        for (Edge w : graph.getAdj(v)) {
            // Se não foi visitado, visito
            if (!visited.contains(w.getW())) {
                // Multiplico o peso do caminho
                BigInteger weight = BigInteger.valueOf((int) w.getWeight());

                // Salvo no caminho do vértice pai
                pathsVisited.get(w.getV()).push(w);
                // Crio o caminho
                Stack<Edge> path = new Stack<>();
                path.push(w);
                pathsVisited.put(w.getW(), path);
                
                dfs(graph, w.getW(), sum_path.multiply(weight), pathsVisited);
            } 
            else if (pathsVisited.containsKey(w.getW())) {
                Stack<Edge> edges = pathsVisited.get(w.getW());
                Stack<Edge> aux = new Stack<>();

                // Descubro o caminho
                Edge edge = edges.pop();
                aux.push(edge);
                while (edge.getW().equals(w.getW())) {
                    // Multiplico o peso do caminho
                    sum_path = sum_path.multiply(BigInteger.valueOf((int) edge.getWeight()));

                    // Salvo o caminho
                    if (!edges.isEmpty()) {
                        edge = edges.pop();
                        aux.push(edge);
                    } else {
                        break;
                    }
                }
                // Somo o peso total do caminho
                this.sum = this.sum.add(sum_path);
                // Reseto o multiplicador do caminho
                sum_path = BigInteger.ONE;

                // Retorno o caminho para a pilha
                while (!aux.isEmpty()) {
                    edges.push(aux.pop());
                }
            }
        }

        System.out.println("Visitados:\n" + pathsVisited.toString());
    }
}