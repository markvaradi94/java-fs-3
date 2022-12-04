package ro.fasttrackit.homework.exercise1.composition;

import lombok.Builder;
import lombok.With;
import ro.fasttrackit.homework.exercise1.person.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@With
@Builder
public record FilePeopleProvider(
        String sourceFile
) implements PeopleProvider {
    @Override
    public List<Person> readPeople() {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            return reader.lines()
                    .map(this::mapToPerson)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
