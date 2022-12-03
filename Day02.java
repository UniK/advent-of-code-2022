///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 17

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.out;

class Day02 {
    public static void main(String... args) throws IOException {
        out.println("Hello Advent of Code 2022: Day Two");

        /* read values from `Day02.input` file */
        Path path = Paths.get("Day02.input");

        Stream<String> lines = Files.lines(path);
        List<Integer> integers = lines
                .map(v -> move(
                        Opponent.valueOf(v.substring(0, 1)),
                        Proponent.valueOf(v.substring(2, 3))))
                .toList();
        lines.close();

        /* return the total of all earned points */
        int sum = integers.stream().mapToInt(Integer::intValue).sum();

        out.println("Part One Answer: " + sum);
    }

    static int move(Opponent o, Proponent p) {
        int LOST = 0;
        int DRAW = 3;
        int WIN = 6;

        if ((o.ordinal() + 1) % 3 == p.ordinal()) {
            out.println(o + " " + p + " It's a win: " + (WIN + p.ordinal() + 1) + " Points");
            return WIN + (p.ordinal() + 1);
        } else if (o.ordinal() == p.ordinal()) {
            out.println(o + " " + p + " Its a draw: " + (DRAW + p.ordinal() + 1) + " Points");
            return DRAW + (p.ordinal() + 1);
        } else {
            out.println(o + " " + p + " It's a loss: " + (LOST + p.ordinal() + 1) + " Points");
            return LOST + (p.ordinal() + 1);
        }
    }

    enum Opponent {
        A("Rock", 1), B("Paper", 2), C("Scissors", 3);

        Opponent(String value, int i) {
        }
    }

    enum Proponent {
        X("Rock", 1), Y("Paper", 2), Z("Scissors", 3);

        Proponent(String value, int i) {
        }
    }
}
