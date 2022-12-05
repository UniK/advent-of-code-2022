///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 17

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

public class Day03 {
    public static void main(String... args) throws IOException {
        out.println("Hello Advent of Code 2022: Day Three");

        /* read values from `Day03.input` file */
        Path path = Paths.get("Day03.input");
        Stream<String> lines = Files.lines(path);
        List<String> stringList = lines.toList();
        lines.close();

        List<Integer> integers = stringList.stream()
                .map(Day03::findFirstCommonCharacter)
                .map(Day03::calcValue)
                .toList();

        int BATCH_SIZE = 3;
        List<Integer> valueOfCommonChars = IntStream.iterate(
                        0,
                        i -> i < stringList.size(),
                        i -> i + BATCH_SIZE)
                // extract the triple (sublist with tree elements) from the initial list
                .mapToObj(i -> stringList.subList(i, Math.min(i + BATCH_SIZE, stringList.size())))
                // find common character
                .map(Day03::findFirstCommonCharacterInThreeLines)
                .map(Day03::calcValue)
                .toList();

        /* results */
        int sum = integers.stream()
                .mapToInt(Integer::intValue)
                .sum();

        out.println("Part One Answer: " + sum);

        int sumPartTwo = valueOfCommonChars.stream()
                .mapToInt(Integer::intValue)
                .sum();

        out.println("Part Two Answer: " + sumPartTwo);
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

    private static Character findFirstCommonCharacterInThreeLines(List<String> triple) {
        Character commonCharacter = triple.get(0).codePoints()
                .mapToObj(c -> (char) c)
                .filter(c ->
                        triple.get(1).indexOf(c) >= 0
                     && triple.get(2).indexOf(c) >= 0)
                .findAny()
                .orElse(null);

        return commonCharacter;
    }

    /* value */
    static int calcValue(Character character) {
        return "_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(character);
    }
}