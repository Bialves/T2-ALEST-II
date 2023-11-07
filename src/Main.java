package src;

import src.API.EdgeWeightedGraph;

public class Main {
    private static EdgeWeightedGraph edgeWeightedGraph;

    public static void main(String[] args) {
        edgeWeightedGraph = new EdgeWeightedGraph("./tests/casof40.txt");
        System.out.println(edgeWeightedGraph.toDot());

        //DepthFirstSearch dfs = new DepthFirstSearch(edgeWeightedGraph, "hidrogenio");
        //System.out.println("Soma total: " + dfs.getSum());
        new Dict(edgeWeightedGraph);

        // Caminhamento em profundidade
        // for(String v: edgeWeightedGraph.getVerts()) {
        // System.out.print(v+ ": ");
        // for(String w: dfs.pathTo(v)) {
        // System.out.print(w+" ");
        // }
        // System.out.println();
        // }
    }
}