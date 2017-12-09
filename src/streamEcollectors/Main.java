package streamEcollectors;

import lambda.Usuario;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Usuario> usuarios = getUsuarios();

        usuarios.stream()
                .sorted(Comparator.comparing(Usuario::getPontos).reversed())
                .limit(5)
                .peek(System.out::println)
                .forEach(Usuario::setModerador);

        System.out.println("\n\n");

        usuarios.forEach(c -> System.out.println(c.getNome() + " - " + c.isModerador()));

        System.out.println("\n\n");

        usuarios = getUsuarios();

        usuarios = usuarios.stream()
                .filter(Main::MoreThan100Score)
                .peek(System.out::println)
                .collect(Collectors.toList());

        usuarios.forEach(Usuario::setModerador);

        usuarios = getUsuarios();
        List<Usuario> usuarioList = usuarios.stream()
                .filter(Main::MoreThan100Score)
                .collect(Collectors.toCollection(ArrayList::new));

        usuarioList.remove(0);

        System.out.println("\n\n");

        usuarioList.forEach(System.out::println);

        usuarios = getUsuarios();

        double average = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .orElse(0.0);

        System.out.println(average);


        usuarios = Collections.EMPTY_LIST;

        average = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .orElse(0.0);

        System.out.println(average);

        usuarios = getUsuarios();

        usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .max()
                .ifPresentOrElse(System.out::println, Main::NotFound);

        usuarios = Collections.EMPTY_LIST;

        usuarios.stream()
                .max(Comparator.comparing(Usuario::getPontos))
                .map(Usuario::getPontos)
                .ifPresentOrElse(System.out::println, Main::NotFound);


        usuarios = getUsuarios();
        Optional<Usuario> any = usuarios.stream()
                .filter(Main::MoreThan30Score)
                .peek(System.out::println)
                .findAny();

        any.ifPresent(System.out::println);


        usuarios = getUsuarios();
        Integer reduce = usuarios.stream()
                .map(Usuario::getPontos)
                .reduce(0, (a, b) -> a + b);
        Optional.ofNullable(reduce).ifPresent(System.out::println);

        usuarios.stream()
                .reduce((usuario, usuario2) -> {
                    if (usuario.getPontos() > usuario2.getPontos())
                        return usuario;
                    return usuario2;
                })
                .ifPresent(System.out::println);

        usuarios.stream()
                .iterator()
                .forEachRemaining(System.out::println);

        boolean hasNome = usuarios.stream()
                .peek(System.out::println)
                .anyMatch(usuario -> usuario.getNome().contains("Nome"));
        System.out.println(hasNome);

        List<Usuario> usuariosOne = getUsuarios();
        List<Usuario> usuariosTwo = getUsuarios();

        Stream<Usuario> usuarioStream = Stream.concat(usuariosOne.stream(), usuariosTwo.stream());

        long count = usuarioStream.count();
        System.out.println(count);


        int i = Stream.generate(Random::new)
                .peek(System.out::println)
                .limit(100)
                .collect(Collectors.toList())
                .get(0)
                .nextInt();

        System.out.println(i);

        List<Long> collect = LongStream.range(0, 100)
                .boxed()
                .peek(System.out::println)
                .filter(Main::isPar)
                .peek(System.out::println)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);

    }

    private static boolean isPar(Long aLong) {
        return aLong % 2 == 0;
    }

    private static boolean MoreThan30Score(Usuario usuario) {
        return usuario.getPontos() > 30;
    }

    private static void NotFound() {
        System.out.println("Not Found");
    }

    private static boolean MoreThan100Score(Usuario usuario) {
        return usuario.getPontos() > 100;
    }

    private static List<Usuario> getUsuarios() {
        return List.of(
                new Usuario("Nome 1", 15, false),
                new Usuario("Nome 2", 17, false),
                new Usuario("Nome 3", 2, false),
                new Usuario("Nome 4", 7, false),
                new Usuario("Nome 5", 310, false),
                new Usuario("Nome 6", 320, false),
                new Usuario("Nome 7", 21, false),
                new Usuario("Nome 8", 0, false)
        );
    }
}
