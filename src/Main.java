package src;

import src.API.DepthFirstSearch;
import src.API.EdgeWeightedGraph;

public class Main {
    private static EdgeWeightedGraph edgeWeightedGraph;

    public static void main(String[] args) {
        edgeWeightedGraph = new EdgeWeightedGraph("./tests/casoteste.txt");
        System.out.println(edgeWeightedGraph.toDot());

        DepthFirstSearch dfs = new DepthFirstSearch(edgeWeightedGraph, "hidrogenio");

        // Caminhamento em profundidade
        // for(String v: edgeWeightedGraph.getVerts()) {
        //     System.out.print(v+ ": ");  
        //     for(String w: dfs.pathTo(v)) {
        //         System.out.print(w+" ");
        //     }
        //     System.out.println();
        // }

        System.out.println("Soma total: " + dfs.getSum());
    }
}