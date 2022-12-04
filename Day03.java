///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 17

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.lang.System.out;

public class Day03 {
    public static void main(String... args) throws IOException {
        out.println("Hello Advent of Code 2022: Day Two");

        /* read values from `Day03.input` file */
        Path path = Paths.get("Day03.input");
        Stream<String> lines = Files.lines(path);
        List<String> stringList = lines.toList();
        lines.close();

        List<Integer> integers = stringList.stream()
                .map(Day03::findFirstCommonCharacter)
                .map(Day03::calcValue)
                .toList();

        /* results */
        int sum = integers.stream()
                .mapToInt(Integer::intValue)
                .sum();

        out.println("Part One Answer: " + sum);
    }

    private static Character findFirstCommonCharacter(String line) {
        String substringFirstHalf = line.substring(0, line.length() / 2);
        String substringSecondHalf = line.substring(line.length() / 2);

        List<Character> collect = substringFirstHalf.codePoints()
                .mapToObj(first -> (char) first)
                .map(c -> substringSecondHalf.codePoints()
                        .mapToObj(second -> (char) second)
                        .filter(x -> Objects.equals(x, c))
                        .findFirst()
                        .orElse(null))
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        return collect.get(0);
    }

    /* value */
    static int calcValue(Character character) {
        return "_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(character);
    }
}