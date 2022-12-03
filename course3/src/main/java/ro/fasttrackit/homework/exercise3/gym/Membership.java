package ro.fasttrackit.homework.exercise3.gym;

import lombok.Builder;

import java.time.Duration;

@Builder(toBuilder = true)
public record Membership(
        GymMember gymMember,
        Duration remainingDuration
) {
}
