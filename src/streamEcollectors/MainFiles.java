package streamEcollectors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MainFiles {
    public static void main(String[] args) throws IOException {
        Files.list(Paths.get("")).forEach(System.out::println);

        Files.find(Paths.get(""), 10, (path, basicFileAttributes) -> path.getFileName().toString().contains("java"))
                .flatMap(MainFiles::toLines)
                .flatMapToInt(String::chars)
                .forEach(System.out::println);

    }

    public static Stream<String> toLines(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }
}
