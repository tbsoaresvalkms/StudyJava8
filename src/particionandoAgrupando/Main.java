package particionandoAgrupando;

import lambda.Usuario;
import streamEcollectors.MainFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Path> files = Files.find(Paths.get(""), 10, (path, basicFileAttributes) -> path.getFileName().toString().contains("java"))
                .collect(Collectors.toList());

        Map<Path, Stream<String>> collect = files.stream()
                .collect(Collectors.toMap(Function.identity(), MainFiles::toLines));
        System.out.println(collect.size());

        Map<Path, Long> countLines = files.stream()
                .collect(Collectors.toMap(Function.identity(), path -> MainFiles.toLines(path).count()));
        System.out.println(countLines.size());


        Usuario paulo_silveira = new Usuario("Paulo Silveira", 150, true);
        Usuario rodrigo_turini = new Usuario("Rodrigo Turini", 120, true);
        Usuario guilherme_silveira = new Usuario("Guilherme Silveira", 90, false);
        Usuario sergio_lopes = new Usuario("Sergio Lopes", 120, false);
        Usuario adriano_almeida = new Usuario("Adriano Almeida", 100, false);

        List<Usuario> usuarios = List.of(paulo_silveira, rodrigo_turini, guilherme_silveira, sergio_lopes, adriano_almeida);

        Map<Integer, List<Usuario>> usuariosPorPontos = usuarios.stream()
                .collect(Collectors.groupingBy(Usuario::getPontos));
        System.out.println(usuariosPorPontos);

        Map<Boolean, List<Usuario>> usuariosModerador = usuarios.stream()
                .collect(Collectors.partitioningBy(Usuario::isModerador));
        System.out.println(usuariosModerador);

        Map<Integer, List<String>> nomePorPontos = usuarios.stream()
                .collect(Collectors.groupingBy(Usuario::getPontos, Collectors.mapping(Usuario::getNome, Collectors.toList())));
        System.out.println(nomePorPontos);

        Map<Boolean, Integer> somaModerador = usuarios.stream()
                .collect(Collectors.partitioningBy(Usuario::isModerador, Collectors.summingInt(Usuario::getPontos)));
        System.out.println(somaModerador);

        Map<Integer, String> nomeJoin = usuarios.stream()
                .collect(Collectors.groupingBy(Usuario::getPontos, Collectors.mapping(Usuario::getNome, Collectors.toList())))
                .entrySet()
                .stream()
                .map(Main::joinName)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(nomeJoin);
    }

    private static Map.Entry<Integer, String> joinName(Map.Entry<Integer, List<String>> integerListEntry) {
        String join = integerListEntry.getValue().stream().collect(Collectors.joining(", "));
        return new AbstractMap.SimpleEntry<>(integerListEntry.getKey(), join);
    }
}
