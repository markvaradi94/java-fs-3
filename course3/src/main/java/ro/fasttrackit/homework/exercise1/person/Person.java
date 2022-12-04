package ro.fasttrackit.homework.exercise1.person;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record Person(
        String firstName,
        String lastName,
        Integer age
) {
    public String fullName() {
        return firstName + " " + lastName;
    }
}
