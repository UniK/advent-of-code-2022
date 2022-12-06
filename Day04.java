///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 17

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.out;

public class Day04 {
    public static void main(String[] args) throws IOException {
        out.println("Hello Advent of Code 2022: Day Four");

        /* read values from `Day04.input` file */
        Path path = Paths.get("Day04.input");
        Stream<String> lines = Files.lines(path);
        List<String> lineList = lines.toList();
        lines.close();

        List<List<String>> sectionList = lineList.stream()
                .peek(out::println)
                .map(line -> List.of(
                        line.substring(0, line.indexOf(",")),
                        line.substring(line.indexOf(",") + 1)))
                .toList();

        List<Boolean> collect = sectionList.stream()
                .map(pair -> {
                    String first = pair.get(0);
                    String second = pair.get(1);
                    return fullyContains(
                            new Pair(
                                    Integer.parseInt(first.substring(0, first.indexOf("-"))),
                                    Integer.parseInt(first.substring(first.indexOf("-") + 1))),
                            new Pair(
                                    Integer.parseInt(second.substring(0, second.indexOf("-"))),
                                    Integer.parseInt(second.substring(second.indexOf("-") + 1))));
                })
                .peek(out::println)
                .toList();

        long count = collect.stream()
                .filter(t -> t)
                .count();

        out.println("\nResult: " + count);
    }

    static boolean fullyContains(Pair x, Pair y) {
        boolean b =
                (y.a >= x.a && y.b <= x.b)
             || (x.a >= y.a && x.b <= y.b);
        return b;
    }

    private static class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
