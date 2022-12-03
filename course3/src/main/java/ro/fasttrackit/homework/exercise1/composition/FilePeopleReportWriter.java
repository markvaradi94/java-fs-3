package ro.fasttrackit.homework.exercise1.composition;

import lombok.Builder;
import lombok.With;
import ro.fasttrackit.homework.exercise1.person.Person;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

import static java.util.stream.Collectors.*;
import static ro.fasttrackit.homework.exercise1.person.AgeGroup.categorizeAge;

@With
@Builder
public record FilePeopleReportWriter(
        String outputFile
) implements PeopleReportWriter {
    @Override
    public void writeReport(List<Person> people) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            people.stream()
                    .collect(
                            groupingBy(person -> categorizeAge(person.age()),
                                    TreeMap::new,
                                    mapping(Person::fullName, joining(", "))))
                    .entrySet()
                    .forEach(entry -> writeLine(writer, entry));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
