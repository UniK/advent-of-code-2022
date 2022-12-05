///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 17

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

class Day01 {
    public static void main(String... args) throws IOException {
        out.println("Hello Advent of Code 2022: Day One");

        /* read values from `Day01.input` file into lists */
        Path path = Paths.get("Day01.input");

        Stream<String> lines = Files.lines(path);
        List<String> collect = lines.collect(Collectors.toList());
        lines.close();

        int[] index = Stream.of(
                        IntStream.of(-1),
                        IntStream.range(0, collect.size())
                                .filter(i -> collect.get(i).isBlank()),
                        IntStream.of(collect.size()))
                .flatMapToInt(s -> s)
                .toArray();

        List<List<String>> subSets = IntStream.range(0, index.length - 1)
                .mapToObj(i -> collect.subList(index[i] + 1, index[i + 1]))
                .toList();

        /* for each list calc the stats */
        List<Long> listOfSums = subSets.stream()
                .map(subset -> subset.stream()
                        .mapToInt(Integer::parseInt)
                        .summaryStatistics()
                        .getSum())
                .sorted(Comparator.reverseOrder())
                .toList();

        /* return the largest sum */
        long result = listOfSums.stream()
                .mapToLong(Long::longValue)
                .summaryStatistics()
                .getMax();

        out.println("Part One Answer: " + result);

        /* return the total of the three largest sums */
        List<Long> threeLargestSums = listOfSums.subList(0, 3);
        long totalOfThreeLargestSums = threeLargestSums.stream()
                .mapToLong(Long::longValue)
                .sum();

        out.println("Part Two Answer: " + totalOfThreeLargestSums);
    }
}