package ro.fasttrackit.homework.exercise1.inheritance;

import ro.fasttrackit.homework.exercise1.person.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FilePeopleReportGenerator extends PersonReportGenerator {
    private final String sourceFile;

    public FilePeopleReportGenerator(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    @Override
    protected List<Person> readPeople() {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            return reader.lines()
                    .map(this::mapToPerson)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
