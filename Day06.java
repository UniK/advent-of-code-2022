///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 17

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

public class Day06 {
    public static void main(String... args) throws IOException {
        out.println("Hello Advent of Code 2022: Day Six");

        /* read values from `Day06.input` file */
        Path path = Paths.get("Day06.input");
        Stream<String> lines = Files.lines(path);
        List<String> lineList = lines.toList();
        lines.close();

        int BLOCK_SIZE = 14;
        for (String line : lineList) {
            int result;
            for (int i = 0, stringLength = line.length() - BLOCK_SIZE; i < stringLength; i++) {

                String substring = line.substring(i, BLOCK_SIZE + i);
                if (!containsDuplicateCharacter(substring)) {
                    result = i + BLOCK_SIZE;
                    out.println("Part One Answer: " + result);

                    break;
                }
            }
        }
    }

    /* check for duplicate chars in a string */
    static boolean containsDuplicateCharacter(String chunk) {
        for (char c : chunk.toCharArray()) {
            List<Integer> indexes = IntStream.range(0, chunk.length())
                    .filter(i -> chunk.charAt(i) == c)
                    .boxed()
                    .toList();
            if (indexes.size() < 2) {
                continue;
            }
            return true;
        }
        return false;
    }
}
