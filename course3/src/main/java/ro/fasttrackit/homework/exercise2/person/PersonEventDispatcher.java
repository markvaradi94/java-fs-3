package ro.fasttrackit.homework.exercise2.person;

import lombok.Builder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;


@Builder
public class PersonEventDispatcher {
    private final Map<Predicate<Person>, Function<Person, String>> logicalSwitch = new LinkedHashMap<>();

    public PersonEventDispatcher() {
        logicalSwitch.put(person -> nameStartsWith(person, "G"), person -> messageForStartsWith(person, "G"));
        logicalSwitch.put(this::isOfVotingAge, this::goVote);
        logicalSwitch.put(person -> isFrom(person, "Oradea"), this::goToUnirii);
    }

    public String dispatch(Person person) {
        return logicalSwitch.entrySet().stream()
                .filter(entry -> entry.getKey().test(person))
                .map(entry -> entry.getValue().apply(person))
                .collect(joining(lineSeparator()));
    }

    private boolean isFrom(Person person, String address) {
        return person.address().equalsIgnoreCase(address);
    }

    private String goToUnirii(Person person) {
        return person.name() + " is going to Unirii.";
    }

    private String goVote(Person person) {
        return person.name() + " can go and vote.";
    }

    private boolean isOfVotingAge(Person person) {
        return person.age() > 18;
    }

    private boolean nameStartsWith(Person person, String letter) {
        return person.name().startsWith(letter);
    }

    private boolean nameStartsWithA(Person person) {
        return person.name().startsWith("A");
    }

    private String messageForStartsWith(Person person, String letter) {
        return person.name() + " starts with " + letter;
    }

    private String messageForStartsWithA(Person person) {
        return person.name() + " starts with A";
    }
}
