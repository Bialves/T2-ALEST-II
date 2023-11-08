package src.API;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Dict {
    private Map<String, BigInteger> sums;
    private final String INIT = "hidrogenio";
    private final String TARGET = "ouro";

    public Dict(EdgeWeightedGraph graph) {
        sums = new HashMap<>();
        BigInteger result = calculateSum(graph, INIT);
        System.out.println("Soma total: " + result);
    }

    private BigInteger calculateSum(EdgeWeightedGraph graph, String v) {
        // Se o vértice atual (v) é o vértice de destino (TARGET), retorna 1, indicando
        // um caminho possível
        if (v.equals(TARGET)) {
            return BigInteger.ONE;
        }

        // Se o valor para o vértice atual já foi calculado, retorna esse valor para
        // evitar recálculos
        if (sums.containsKey(v)) {
            return sums.get(v);
        }

        // Inicializa a soma em zero
        BigInteger sum = BigInteger.ZERO;

        // Para cada aresta (Edge) adjacente ao vértice atual...
        for (Edge w : graph.getAdj(v)) {
            // Chama recursivamente a função para o vértice de destino da aresta

            // Calcula a soma total para o vértice de destino
            // Multiplica a soma parcial pelo peso da aresta e adiciona ao total
            sum = sum.add(calculateSum(graph, w.getW()).multiply(BigInteger.valueOf((int) w.getWeight())));
        }

        // Armazena o resultado (sum) no mapa para evitar futuros recálculos
        // desnecessários
        sums.put(v, sum);

        // Retorna a soma total pro vértice atual
        return sum;
    }

    @Override
    public String toString() {
        return sums.toString();
    }
}
