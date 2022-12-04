package ro.fasttrackit.homework.exercise2;

import ro.fasttrackit.homework.exercise2.person.Person;
import ro.fasttrackit.homework.exercise2.person.PersonEventDispatcher;

public class Exercise2Main {
    public static void main(String[] args) {
        Person gyuszika = Person.builder()
                .name("Gyuszi")
                .age(26)
                .address("Oradea")
                .build();

        Person gyulacska = Person.builder()
                .name("Gyula")
                .age(18)
                .address("Salonta")
                .build();

        var dispatcher = new PersonEventDispatcher();

        System.out.println(dispatcher.dispatch(gyuszika));
        System.out.println("\n" + dispatcher.dispatch(gyulacska));
    }
}
