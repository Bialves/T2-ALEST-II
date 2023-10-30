public class Main {
    private static Digraph digraph;

    public static void main(String[] args) {
        digraph = new Digraph("Tests/basic.txt");
        System.out.println(digraph.toDot());
    }
}