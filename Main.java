import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        reader("Tests/basic.txt");
    }

    public static String reader(String path) {
        String input = null;

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(path, Charset.defaultCharset())));
            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(" -> ");
                // System.out.println("linha =" + line[0] + "," + line[1]);

                String[] ant = line[0].split("\\s");
                String[] next = line[1].split("\\s");
                String value = (next[0]);
                String element = next[1];

                System.out.print(value + " " + element + " <- ");
                value = (ant[0]);
                element = ant[1];
                System.out.println(value + " " + element);

                if (ant.length > 2) {
                    int count = 0;
                    while (count < (ant.length - 1)) {
                        value = (ant[count]);
                        element = ant[count + 1];
                        count = count + 2;
                        System.out.println(value + " " + element);
                    }
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

        return input;
    }
}