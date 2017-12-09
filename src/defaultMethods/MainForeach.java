package defaultMethods;

import lambda.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MainForeach {
    public static void main(String[] args) {
        consumerAndThenConsumer();
        removeIfWithPredicate();
        removeIfWithPredicateAndLogicsOperator();


    }

    private static void consumerAndThenConsumer() {
        newPrint("Usando dois consumers no foreach");

        List<Usuario> usuarios = buildList();
        Consumer<Usuario> lambdaConsumer = u -> System.out.println(u.getNome());
        Consumer<Usuario> lambdaGetModerador = u -> System.out.println(u.isModerador());
        usuarios.forEach(lambdaConsumer.andThen(lambdaGetModerador));
    }

    private static void removeIfWithPredicate() {
        newPrint("Remocao com predicate");
        List<Usuario> usuarios = buildList();
        usuarios.removeIf(new RemoverPontuacaoBaixa(150));
        Consumer<Usuario> lambdaConsumer = u -> System.out.println(u.getNome());
        usuarios.forEach(lambdaConsumer);
    }

    private static void removeIfWithPredicateAndLogicsOperator() {
        newPrint("Remocao com dois predicate");
        List<Usuario> usuarios = buildList();

        Predicate<Usuario> notaBaixa = usuario -> usuario.getPontos() <= 150;
        Predicate<Usuario> naoModerador = usuario -> !usuario.isModerador();
        usuarios.removeIf(notaBaixa.and(naoModerador));

        Consumer<Usuario> lambdaConsumer = u -> System.out.println(u.getNome());
        usuarios.forEach(lambdaConsumer);
    }

    private static void newPrint(String msg) {
        System.out.println("\n\n\n" + msg);
    }

    private static List<Usuario> buildList() {
        Usuario paulo = new Usuario("Paulo", 150, true);
        Usuario rodrigo = new Usuario("Rodrigo", 120, false);
        Usuario guilherme = new Usuario("Guilherme", 190, false);

        return new ArrayList<>(Arrays.asList(paulo, rodrigo, guilherme));
    }
}
