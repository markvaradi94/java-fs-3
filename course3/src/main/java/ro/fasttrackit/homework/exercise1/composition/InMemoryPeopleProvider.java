package ro.fasttrackit.homework.exercise1.composition;

import lombok.Builder;
import ro.fasttrackit.homework.exercise1.person.Person;

import java.util.List;

@Builder
public class InMemoryPeopleProvider implements PeopleProvider {
    @Override
    public List<Person> readPeople() {
        return List.of(
                Person.builder()
                        .firstName("Gyuszi")
                        .lastName("Kovacs")
                        .age(25)
                        .build(),
                Person.builder()
                        .firstName("Laci")
                        .lastName("Szabo")
                        .age(47)
                        .build(),
                Person.builder()
                        .firstName("Ion")
                        .lastName("Popescu")
                        .age(55)
                        .build(),
                Person.builder()
                        .firstName("Ghita")
                        .lastName("Munteanu")
                        .age(44)
                        .build(),
                Person.builder()
                        .firstName("Karcsi")
                        .lastName("Asztalos")
                        .age(17)
                        .build(),
                Person.builder()
                        .firstName("Sebastian")
                        .lastName("Dobre")
                        .age(67)
                        .build()
        );
    }
}
