package ro.fasttrackit.homework.exercise1.inheritance;

import lombok.SneakyThrows;
import ro.fasttrackit.homework.exercise1.person.Person;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.*;
import static ro.fasttrackit.homework.exercise1.person.AgeGroup.categorizeAge;

abstract class PersonReportGenerator {

    protected abstract List<Person> readPeople();

    @SneakyThrows
    public void generateReport(String outputFile) {
        List<Person> people = readPeople();
        writeReport(people, outputFile);
    }

    Person mapToPerson(String line) {
        String[] tokens = line.split(",");
        return Person.builder()
                .firstName(tokens[0])
                .lastName(tokens[1])
                .age(parseInt(tokens[2]))
                .build();
    }

    private void writeReport(List<Person> people, String outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            people.stream()
                    .collect(
                            groupingBy(person -> categorizeAge(person.age()),
                                    TreeMap::new,
                                    mapping(Person::fullName, joining(", "))))
                    .entrySet()
                    .forEach(entry -> writeLine(writer, entry));
        }
    }

    private void writeLine(BufferedWriter writer, Map.Entry<String, String> entry) {
        try {
            writer.write(entry.getKey() + ": " + entry.getValue());
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
