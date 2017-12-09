package methodReference;

import lambda.Usuario;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Usuario paulo = new Usuario("Paulo", 150, true);
        Usuario rodrigo = new Usuario("Rodrigo", 120, false);
        Usuario guilherme = new Usuario("Guilherme", 190, false);
        Usuario carla = new Usuario("Carla", 290, false);
        Usuario zacarias = new Usuario("Zacarias", 90, false);

        newPrint("");
        List<Usuario> usuarios = new ArrayList<>(List.of(paulo, rodrigo, carla, zacarias, guilherme));
        usuarios.sort((o1, o2) -> o1.getNome().compareTo(o2.getNome()));
        usuarios.forEach(System.out::println);

        newPrint("");
        usuarios.sort(Comparator.comparing(Usuario::getNome).reversed());
        usuarios.forEach(System.out::println);

        newPrint("");
        usuarios.sort(Comparator.comparingInt(Usuario::getPontos).reversed());
        usuarios.forEach(System.out::println);

        newPrint("");
        usuarios.sort(Comparator.naturalOrder());
        usuarios.forEach(System.out::println);
    }

    private static void newPrint(String msg) {
        System.out.println("\n\n\n" + msg);
    }
}
