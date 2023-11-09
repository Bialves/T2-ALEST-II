package src.API;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class EdgeWeightedGraph {
  protected static final String NEWLINE = System.getProperty("line.separator");
  protected Map<String, List<Edge>> graph;

  public EdgeWeightedGraph(String filename) {
    graph = new HashMap<>();
    reader(filename);
  }

  public void addEdge(String v, String w, double weight) {
    Edge e = new Edge(v, w, weight);
    addToList(v, e);
  }

  public Iterable<Edge> getAdj(String v) {
    return graph.get(v);
  }

  public Set<String> getVerts() {
    return graph.keySet();
  }

  public String toDot() {
    // Usa um conjunto de arestas para evitar duplicatas
    Set<String> edges = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    sb.append("graph {" + NEWLINE);
    sb.append("rankdir = TB;" + NEWLINE);
    sb.append("node [shape = circle];" + NEWLINE);
    for (String v : getVerts().stream().sorted().toList()) {
      for (Edge e : getAdj(v)) {
        String edge = e.toString();
        if (!edges.contains(edge)) {
          sb.append(String.format("%s -- %s [label=\"%.3f\"]", e.getV(), e.getW(), e.getWeight()) + NEWLINE);
          edges.add(edge);
        }
      }
    }
    sb.append("}" + NEWLINE);
    return sb.toString();
  }

  // Adiciona um vértice adjacente a outro, criando a lista
  // de adjacências caso ainda não exista no dicionário
  protected List<Edge> addToList(String v, Edge e) {
    List<Edge> list = graph.get(v);
    if (list == null)
      list = new LinkedList<>();
    list.add(e);
    graph.put(v, list);
    return list;
  }

  // Método de leitura dos casos testes
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
        String e = next[1];

        if (ant.length > 2) {
          int count = 0;
          while (count < (ant.length - 1)) {
            value = Integer.parseInt(ant[count]);
            element = ant[count + 1];
            addEdge(element, e, value);
            count = count + 2;
          }
        } else {
          value = Integer.parseInt(ant[0]);
          element = ant[1];
          addEdge(element, e, value);
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