package src;

import src.API.Dict;
import src.API.EdgeWeightedGraph;

public class Main {
    private static EdgeWeightedGraph edgeWeightedGraph;

    public static void main(String[] args) {
        edgeWeightedGraph = new EdgeWeightedGraph("./tests/casoteste.txt");
        System.out.println(edgeWeightedGraph.toDot());

        // DepthFirstSearch dfs = new DepthFirstSearch(edgeWeightedGraph, "hidrogenio");
        // System.out.println("Soma total: " + dfs.getSum());
        Dict d = new Dict(edgeWeightedGraph);
        System.out.println(d.toString());
    }
}