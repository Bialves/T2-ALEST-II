package src.API;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class RecursiveSum {
  private Map<String, BigInteger> sums;
  private final String INIT = "hidrogenio";
  private final String TARGET = "ouro";

  public RecursiveSum(EdgeWeightedGraph graph) {
    sums = new HashMap<>();
    // Por padrão, Ouro tem peso 1
    sums.put(TARGET, BigInteger.ONE);
    BigInteger result = calculate(graph, INIT);
    System.out.println("Total de Hidrogênios: " + result);
  }

  private BigInteger calculate(EdgeWeightedGraph graph, String v) {
    /*
     * > Condição de parada:
     * Se o valor para o vértice atual já foi calculado, retorna esse valor.
     */
    if (sums.containsKey(v)) {
      return sums.get(v);
    }

    // Inicializa a soma do caminho em zero
    BigInteger sum_path = BigInteger.ZERO;

    // Visita os vértices adj ao atual
    for (Edge w : graph.getAdj(v)) {
      /*
       * Chama, recursivamente, a função para os vértices adj,
       * até encontrar o ponto de parada e retornar.
       * 
       * Desse modo, realiza uma DFS
       */

      /*
       * Calcula a soma do produto do caminho para o vértice de destino.
       * (Multiplicando os adj do INIT até o TARGET, e somando esse produto
       * a outro produto de caminho de INIT até TARGET)
       */
      sum_path = sum_path.add(calculate(graph, w.getW()).multiply(BigInteger.valueOf((int) w.getWeight())));
    }

    // Armazena o resultado (sum_path) no mapa para evitar futuros recálculos
    sums.put(v, sum_path);

    // Retorna o produto total do caminho para o vértice atual
    return sum_path;
  }

  @Override
  public String toString() {
    return sums.toString();
  }
}