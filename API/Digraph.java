import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class Digraph extends Graph {

  public Digraph(String filename) {
    super(filename);
    reader(filename);
  }

  @Override
  public void addEdge(String v, String w) {
    addToList(v, w);
  }

  @Override
  public String toDot() {
    Set<String> addedEdges = new HashSet<>();

    StringBuilder sb = new StringBuilder();
    sb.append("digraph {" + NEWLINE);
    sb.append("rankdir = TB;" + NEWLINE);
    sb.append("node [shape = circle];" + NEWLINE);
    for (String v : getVerts().stream().sorted().toList()) {
      for (String w : getAdj(v)) {
        String edge = v + " -> " + w;
        if (!addedEdges.contains(edge) && shouldIncludeEdge(v, w)) {
          sb.append(edge + NEWLINE);
          addedEdges.add(edge);
        }
      }
    }
    sb.append("}" + NEWLINE);
    return sb.toString();
  }

  private boolean shouldIncludeEdge(String v, String w) {
    // Verifica se a aresta contém números
    if (v.matches(".*\\d.*") || w.matches(".*\\d.*")) {
      return false; // Não inclua arestas que contenham números
    }
    return true; // Inclua as outras arestas
  }

  @SuppressWarnings("unused")
  public void reader(String path) {

    try {
      Scanner sc = new Scanner(new BufferedReader(new FileReader(path, Charset.defaultCharset())));
      while (sc.hasNext()) {
        String[] line = sc.nextLine().split(" -> ");
        // System.out.println("linha =" + line[0] + "," + line[1]);

        String[] ant = line[0].split("\\s");
        String[] next = line[1].split("\\s");
        int value;
        String element = "";
        int v = Integer.parseInt(next[0]);
        String e = next[1];

        if (ant.length > 2) {
          int count = 0;
          while (count < (ant.length - 1)) {
            value = Integer.parseInt(ant[count]);
            element = ant[count + 1];
            addEdge(element, e);
            count = count + 2;
          }
        } else {
          value = Integer.parseInt(ant[0]);
          element = ant[1];
          addEdge(element, e);
        }
      }
    } catch (FileNotFoundException e) {
      System.err.format("Arquivo não encontrado: %s%n", e);
    } catch (IOException e) {
      System.err.format("Erro de E/S: %s%n", e);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.err.format("Não é possível ler mais linhas: %s%n", e);
    } catch (Exception e) {
      System.err.format("Erro: %s%n", e.getMessage());
    }
  }
}