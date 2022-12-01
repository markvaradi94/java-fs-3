package ro.fasttrackit.curs3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Java10Features {
    public static void main(String[] args) {
        localTypeInference();
        copyOfCollections();
    }

    private static void copyOfCollections() {
        List<String> firstCollection = List.of("name");
        List<String> collected = firstCollection
                .stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        collected.add("bla");
        System.out.println(firstCollection);
        System.out.println(collected);
        List.copyOf(collected).add("9877");
    }

    private static void localTypeInference() {
        int a = 1;
        var b = 2;

        var map = Map.of("a", Map.of("b", List.of(2)));

        int[] arr = {1, 2, 3, 4};
        int arr2[] = {1, 2, 3, 4, 5};
        var bc = new int[2];
        var arrInit = new int[]{1, 2, 3, 4};

        var hack = new MyInterface() {
            int myField = 2;

            @Override
            public String sayHello(String name) {
                return "wazzaaa";
            }

            public String burp() {
                return "Some burp";
            }
        };

        System.out.println(hack.getClass());
        System.out.println(hack.myField);
        System.out.println(hack.burp());
    }
}
