public class Main {
    private static EdgeWeightedGraph edgeWeightedGraph;

    public static void main(String[] args) {
        edgeWeightedGraph = new EdgeWeightedGraph("Tests/basic.txt");
        System.out.println(edgeWeightedGraph.toDot());
    }
}