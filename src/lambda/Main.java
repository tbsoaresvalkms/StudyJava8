package lambda;

import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Usuario paulo = new Usuario("Paulo", 150, true);
        Usuario rodrigo = new Usuario("Rodrigo", 120, false);
        Usuario guilherme = new Usuario("Guilherme", 190, false);

        List<Usuario> usuarios = List.of(paulo, rodrigo, guilherme);

        newPrint("Foreach tradicional:");
        for (Usuario u : usuarios) {
            System.out.println(u.getNome());
        }

        newPrint("Foreach da collections, recebendo um consumer:");
        usuarios.forEach(new MostradorConsumer());

        newPrint("Foreach com lambda");
        Consumer<Usuario> lambdaConsumer = u -> System.out.println(u.getNome());
        usuarios.forEach(lambdaConsumer);

        Consumer<Usuario> lambdaSetModerador = u -> u.setModerador();
        usuarios.forEach(lambdaSetModerador);

        Consumer<Usuario> lambdaGetModerador = u -> System.out.println(u.isModerador());
        usuarios.forEach(lambdaGetModerador);
    }

    private static void newPrint(String msg) {
        System.out.println("\n\n\n" + msg);
    }
}
