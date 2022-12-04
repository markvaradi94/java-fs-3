package ro.fasttrackit.homework.exercise1.composition;

import ro.fasttrackit.homework.exercise1.person.Person;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PeopleReportWriter {
    void writeReport(List<Person> people);

    default void writeLine(BufferedWriter writer, Map.Entry<String, String> entry) {
        try {
            writer.write(entry.getKey() + ": " + entry.getValue());
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
