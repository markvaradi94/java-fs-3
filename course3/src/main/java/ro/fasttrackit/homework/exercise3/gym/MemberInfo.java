package ro.fasttrackit.homework.exercise3.gym;

import lombok.Builder;

import java.time.Duration;
import java.time.LocalDate;

@Builder(toBuilder = true)
public record MemberInfo(
        String ownerName,
        LocalDate birthDate,
        String remainingMembership
) {
}
