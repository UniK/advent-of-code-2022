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

class Day02 {
    public static void main(String... args) throws IOException {
        out.println("Hello Advent of Code 2022: Day Two");

        /* read values from `Day02.input` file */
        Path path = Paths.get("Day02.input");
        Stream<String> lines = Files.lines(path);
        List<String> stringList = lines.toList();
        lines.close();

        List<Integer> integers = stringList.stream()
                .map(line -> move(
                        Opponent.valueOf(line.substring(0, 1)),
                        Proponent.valueOf(line.substring(2, 3))))
                .toList();

        List<Integer> strategyResultList = stringList.stream()
                .map(line -> strategy(
                        Opponent.valueOf(line.substring(0, 1)),
                        Strategy.valueOf(line.substring(2, 3))))
                .toList();

        /* return the total of all earned points */
        int sum = integers.stream()
                .mapToInt(Integer::intValue)
                .sum();

        out.println("Part One Answer: " + sum);

        /* return the total of all earned point by using suggested strategy */
        int sumStrategy = strategyResultList.stream()
                .mapToInt(Integer::intValue)
                .sum();

        out.println("Part Two Answer: " + sumStrategy);
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

    static int strategy(Opponent o, Strategy strategy) {
        if (Objects.equals(strategy.toString(), Strategy.X.toString())) {
            Proponent proponent = o == Opponent.A
                    ? Proponent.Z
                    : Proponent.values()[o.ordinal() - 1];
            return move(o, proponent);
        } else if (Objects.equals(strategy.toString(), Strategy.Y.toString())) {
            return move(o, Proponent.values()[o.ordinal()]);
        } else {
            Proponent proponent = o == Opponent.C
                    ? Proponent.X
                    : Proponent.values()[o.ordinal() + 1];
            return move(o, proponent);
        }
    }

    enum Opponent {
        A("Rock"), B("Paper"), C("Scissors");

        Opponent(String value) {}
    }

    enum Proponent {
        X("Rock"), Y("Paper"), Z("Scissors");

        Proponent(String value) {}
    }

    enum Strategy {
        X("Loss"), Y("Draw"), Z("Win");

        Strategy(String value) {}
    }
}
