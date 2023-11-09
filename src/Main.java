package src;

import src.API.RecursiveSum;
import src.API.EdgeWeightedGraph;

public class Main {
  public static void main(String[] args) {
    EdgeWeightedGraph graph = new EdgeWeightedGraph("./tests/casoteste.txt");
    System.out.println(graph.toDot());

    long init = System.currentTimeMillis();
    new RecursiveSum(graph);
    long end = System.currentTimeMillis();
    System.out.println("Tempo: " + (end - init) + " ms");
  }
}