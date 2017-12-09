package defaultMethods;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MainMap {
    public static void main(String[] args) {
        Map<String, Integer> eleicao = new HashMap<>(Map.of("Joao", 10, "Bruno", 20, "Camila", 5));


        newPrint("Valor ja existe");
        eleicao.putIfAbsent("Joao", 20);
        eleicao.forEach((k, v) -> System.out.println(k + " - " + v));

        newPrint("Valor nao existe");
        eleicao.putIfAbsent("Pedro", 40);
        eleicao.forEach((k, v) -> System.out.println(k + " - " + v));

        newPrint("Se joao estiver, faca");
        Integer novoValor = eleicao.computeIfPresent("Joao", (k, v) -> {
            System.out.println(k + " ficha suja, perde 10 votos.");
            return 0;
        });

        System.out.println(novoValor);
        eleicao.forEach((k, v) -> System.out.println(k + " - " + v));

        newPrint("Se Thiago nao estiver, faca ele com 10 a mais que o maior atual");
        eleicao.computeIfAbsent("Thiago", (String k) -> {
            Optional<Map.Entry<String, Integer>> max = eleicao
                    .entrySet()
                    .stream()
                    .max(Comparator.comparing(Map.Entry::getValue));

            return max.get().getValue() + 10;
        });
        eleicao.forEach((k, v) -> System.out.println(k + " - " + v));
    }


    private static void newPrint(String msg) {
        System.out.println("\n\n\n" + msg);
    }
}
