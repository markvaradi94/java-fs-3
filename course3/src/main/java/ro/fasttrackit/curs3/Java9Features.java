package ro.fasttrackit.curs3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java9Features {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        processApi();
        tryWithResourcesImprovements();
        optionalMethod();
        streamImprovements();
        httpClient();
    }

    private static void httpClient() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/get"))
                .GET()
                .build();

        String body = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString())
                .body();

        System.out.println("\n" + body);
    }

    private static void streamImprovements() {
        double average = IntStream.rangeClosed(1, 100)
                .average()
                .orElse(0);
        System.out.println("\n" + average);

//        IntStream.iterate(0, i -> i < 100, i -> i + 1)
//                .forEach(System.out::println);

        System.out.println("--------------------------");
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .takeWhile(i -> i < 5)
                .map(i -> i + " elem")
                .collect(Collectors.joining(", ")));
    }

    private static void optionalMethod() {
        Optional.ofNullable(null)
                .ifPresentOrElse(value -> System.out.println(value),
                        () -> System.out.println("Does not exist"));
    }

    private static void tryWithResourcesImprovements() throws IOException {
        BufferedWriter writer1 = new BufferedWriter(new FileWriter("src/main/resources/test.txt"));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter("src/main/resources/test2.txt"));
        try (writer1; writer2) {
            writer1.write("Break compilation");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void processApi() {
        ProcessHandle current = ProcessHandle.current();
        System.out.println(current.info());

        ProcessHandle.of(27472)
                .ifPresent(Java9Features::doSomethingWithProcess);
    }

    private static void doSomethingWithProcess(ProcessHandle process) {
        CompletableFuture<ProcessHandle> async = process.onExit();
        async.thenAccept(smallProcess -> System.out.println("Process: " + smallProcess.pid() + " died."));
    }
}

interface DataProvider {
    default String provideData() {
        String name = getName();
        return "hello" + name;
    }

    private String getName() {
        return "Gyuszi";
    }
}

class MySmallProcess {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(ProcessHandle.current().pid());
        Thread.sleep(10000000L);
    }
}
