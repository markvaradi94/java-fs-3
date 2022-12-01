package ro.fasttrackit.curs3;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.time.Month.DECEMBER;
import static java.time.ZoneOffset.UTC;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Java8Features {
    public static void main(String[] args) {
        preLambdas();
        lambdas();
        streams();
        optional();
        methodRefs();
        timeApi();
    }

    private static void timeApi() {
        LocalDate onlyDate = LocalDate.of(2022, DECEMBER, 15);
        LocalDateTime dateTime = LocalDateTime.now();
        Date date = new Date(dateTime.toInstant(UTC).toEpochMilli());

        Date functionalDate = ofNullable(dateTime.toInstant(UTC))
                .map(Instant::toEpochMilli)
                .map(Date::new)
                .orElse(new Date());

        Date dateTheThird = of(dateTime)
                .map(dt -> dt.toInstant(UTC))
                .map(Instant::toEpochMilli)
                .map(Date::new)
                .orElse(new Date());
    }

    private static void methodRefs() {
        List<Integer> lengths = names().stream()
                .map(String::length)
                .sorted(Comparator.naturalOrder())
//                .sorted(Comparator.reverseOrder())
                .collect(toList());
        System.out.println(lengths);
    }

    private static void optional() {
        names().stream()
                .filter(name -> name.equalsIgnoreCase("Vadim Tudor"))
                .map(name -> "Vodim Todor")
                .findFirst()
                .ifPresent(System.out::println);
    }

    private static void streams() {
        System.out.println();
        names().stream()
                .filter(name -> name.length() < 10)
                .map(Java8Features::abbreviate)
                .forEach(System.out::println);

        Map<Character, List<String>> byFirstLetter = names().stream().parallel()
                .peek(n -> showThread())
                .collect(groupingBy(name -> name.charAt(0)));
        System.out.println("\n" + byFirstLetter);
    }

    private static void showThread() {
        System.out.println(Thread.currentThread().getName());
    }

    private static String abbreviate(String name) {
        String[] parts = name.split(" ");
        return parts[0] + " " + parts[1].charAt(0) + ".";
    }

    private static List<String> names() {
        return List.of(
                "Some name",
                "Gicu Pop",
                "Karcsi Szilagyi",
                "Mike Hunt",
                "Shia Lebouef",
                "Jim Carrey",
                "Veta Biris",
                "Ion Pop"
        );
    }

    private static void lambdas() {
        MyInterface c = name -> "hola, " + name;

        use(n -> n + "ceva");
    }

    private static void preLambdas() {
        A a = new A();
        System.out.println(a.sayHello("Gyuszi"));

        MyInterface b = new MyInterface() {
            @Override
            public String sayHello(String name) {
                return "szia te, " + name;
            }
        };

        use(new MyInterface() {
            @Override
            public String sayHello(String name) {
                return name + "something";
            }
        });
    }

    private static void use(MyInterface logic) {

    }
}

@FunctionalInterface
interface MyInterface {
    String NAME_CONSTANT = "MyInterface";

    String sayHello(String name);

    default String withImplementation() {
        return "hello";
    }
}

class A implements MyInterface {

    @Override
    public String sayHello(String name) {
        return "servus " + name;
    }
}
