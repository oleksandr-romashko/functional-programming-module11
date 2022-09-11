package global.goit.romashko;

import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(java.lang.String[] args) {
        List<java.lang.String> employees = new ArrayList<>();
        employees.add("Bohdan");
        employees.add("Zak");
        employees.add("Lena");
        employees.add("Marta");
        employees.add("Beda");
        employees.add("Mikhael");
        employees.add("Sam");
        employees.add("Astra");
        employees.add("Anna");
        employees.add("Rostislav");

        //Task No. 1
        String resultTask1 = getOddNames(employees);

        System.out.println("Task 1:");
        System.out.println("    Input names list:");
        System.out.println("        " + employees);
        System.out.println("    Result string:");
        System.out.println("        " + resultTask1);

        System.out.println();

        //Task No. 2
        List<String> resultTask2 = getUpperCaseListDescOrder(employees);

        System.out.println("Task 2:");
        System.out.println("    Input string list:");
        System.out.println("        " + employees);
        System.out.println("    Result string:");
        System.out.println("        " + resultTask2);

        System.out.println();

        //Task No. 3
        String[] input = {"1, 2, 0", "4, 5"};
        String resultTask3 = getCombinedAndSorted(input);

        System.out.println("Task 3:");
        System.out.println("    Input array:");
        System.out.println("        " + Arrays.toString(input));
        System.out.println("    Result sorted numbers string:");
        System.out.println("        " + resultTask3);

        System.out.println();

        //Task No. 4
        final long a = 25214903917L;
        final long c = 11L;
        final long m = (long) Math.pow(2,48);
        final long seed = 0;
        final int numberOfPrintedValues = 12;

        Stream<Long> randomStream = getPseudoRandomStream(a, c, m, seed);
        List<Long> resultTask4List = randomStream
                .limit(numberOfPrintedValues).toList();

        System.out.println("Task 4:");
        System.out.println("    Input values: a = " + a + ", c = " + c + ", m = " + m + ", seed = " + seed);
        System.out.println("    Result (print first " + numberOfPrintedValues + " values):");
        System.out.println("        " + resultTask4List);

        System.out.println();

        //Task No. 5
        List<String> firstList = List.of("One", "Three", "Five", "Seven");
        List<String> secondList = List.of("Two", "Four", "Six");

        Stream<String> firstStream = firstList.stream();
        Stream<String> secondStream = secondList.stream();
        Stream<String> zipResultStream = zip(firstStream, secondStream);

        System.out.println("Task 4:");
        System.out.println("    Input first stream:");
        System.out.println("        " + firstList);
        System.out.println("    Input second stream:");
        System.out.println("        " + secondList);
        System.out.println("    Result mixed stream:");
        System.out.println("        " + zipResultStream.toList());
    }

    //Solution to Task No. 1
    public static String getOddNames(List<String> names) {
        return IntStream
                .range(0, names.size())
                .mapToObj(index -> index + ". " + names.get(index))
                .filter(name -> {
                    String stringNumber = name.substring(0, name.indexOf('.'));
                    int number = Integer.parseInt(stringNumber);
                    return number % 2 != 0;
                })
                .collect(Collectors.joining(", "));
    }

    //Solution to Task No. 2
    public static List<String> getUpperCaseListDescOrder(List<String> strings) {
        return strings
                .stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    //Solution to Task No. 3
    public static String getCombinedAndSorted(String[] input) {
        return Stream.of(input)
                .flatMap(array -> Stream.of(array.split(", ")))
                .mapToInt(Integer::parseInt)
                .sorted()
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    //Solution to Task No. 4
    //Using Linear congruential generator (LCG)
    public static Stream<Long> getPseudoRandomStream(long a, long c, long m, long seed) {
        return Stream.iterate(seed, n -> (a * n + c) % m);
    }

    //Solution to Task No. 5
    //Interleave (merge) streams
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iteratorFirst = first.iterator();
        Iterator<T> iteratorSecond = second.iterator();

        Stream.Builder<T> builder = Stream.builder();

        while (iteratorFirst.hasNext() && iteratorSecond.hasNext()) {
            builder.add(iteratorFirst.next());
            builder.add(iteratorSecond.next());
        }

        return builder.build();
    }
}