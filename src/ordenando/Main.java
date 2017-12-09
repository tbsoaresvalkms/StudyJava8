package ordenando;

import lambda.Usuario;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Usuario paulo = new Usuario("Paulo", 150, true);
        Usuario rodrigo = new Usuario("Rodrigo", 120, true);
        Usuario guilherme = new Usuario("Guilherme", 120, true);
        Usuario carla = new Usuario("Carla", 290, false);
        Usuario zacarias = new Usuario("Zacarias", 120, false);

        newPrint("");
        List<Usuario> usuarios = new ArrayList<>(List.of(paulo, rodrigo, carla, zacarias, guilherme));
        usuarios.sort(
                Comparator.comparingInt(Usuario::getPontos).reversed()
                        .thenComparing(Usuario::isModerador)
                        .thenComparing(Usuario::getNome)
        );
        usuarios.forEach(System.out::println);

        newPrint("");
        usuarios = new ArrayList<>(List.of(paulo, rodrigo, carla, zacarias, guilherme));
        usuarios.sort(Comparator.nullsLast(Comparator.comparing(Usuario::getNome)));
        usuarios.forEach(System.out::println);


        Clazz clazz = new Clazz();

        Consumer<Clazz> noReturnNoParams = Clazz::noReturnNoParams;
        noReturnNoParams.accept(new Clazz());

        BiConsumer<Clazz, String> noReturnYesParams = Clazz::noReturnYesParams;
        noReturnYesParams.accept(new Clazz(), "params");

        Function<Clazz, String> yesReturnNoParams = Clazz::yesReturnNoParams;
        String apply = yesReturnNoParams.apply(new Clazz());

        BiFunction<Clazz, String, String> yesReturnYesParams = Clazz::yesReturnYesParams;
        String params = yesReturnYesParams.apply(new Clazz(), "params");

        Runnable noReturnNoParams1 = clazz::noReturnNoParams;
        noReturnNoParams1.run();

        Consumer<String> noReturnYesParams1 = clazz::noReturnYesParams;
        noReturnYesParams1.accept("params");

        Runnable yesReturnNoParams1 = clazz::yesReturnNoParams;
        yesReturnNoParams1.run();

        Function<String, String> yesReturnYesParams1 = clazz::yesReturnYesParams;
        String params1 = yesReturnYesParams1.apply("params");

        Supplier<Clazz> aNew = Clazz::new;
        Clazz clazz1 = aNew.get();

    }

    private static void newPrint(String msg) {
        System.out.println("\n\n\n" + msg);
    }
}

class Clazz {
    void noReturnNoParams() {
    }

    void noReturnYesParams(String param) {
    }

    String yesReturnNoParams() {
        return "";
    }

    String yesReturnYesParams(String params) {
        return "";
    }

}
