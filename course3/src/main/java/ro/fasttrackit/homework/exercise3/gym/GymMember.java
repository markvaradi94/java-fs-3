package ro.fasttrackit.homework.exercise3.gym;

import lombok.Builder;
import lombok.With;

import java.time.LocalDate;

@With
@Builder
public record GymMember(
        String name,
        LocalDate birthDate
) {
}
