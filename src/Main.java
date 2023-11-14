package src;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import src.API.EdgeWeightedGraph;

public class Main {
  public static void main(String[] args) {
    tests();
  }

  private static void tests() {
    try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("./tests/"))) {
      for (Path file : stream) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(file.toFile().getAbsolutePath());

        long init = System.currentTimeMillis();
        new RecursiveSum(graph);
        long end = System.currentTimeMillis();
        System.out.println("Teste:" + file.getFileName() +" | Tempo: " + (end - init) + " ms");
      }
    } catch (IOException | DirectoryIteratorException e) {
      System.err.println(e.getMessage());
    }
  }
}