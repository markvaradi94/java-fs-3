package ro.fasttrackit.homework.exercise1.composition;

import ro.fasttrackit.homework.exercise1.person.Person;

import java.util.List;

import static java.lang.Integer.parseInt;

public interface PeopleProvider {
    List<Person> readPeople();

    default Person mapToPerson(String line) {
        String[] tokens = line.split(",");
        return Person.builder()
                .firstName(tokens[0])
                .lastName(tokens[1])
                .age(parseInt(tokens[2]))
                .build();
    }
}
