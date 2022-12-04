package ro.fasttrackit.homework.exercise2.person;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record Person(
        String name,
        int age,
        String address
) {
}
