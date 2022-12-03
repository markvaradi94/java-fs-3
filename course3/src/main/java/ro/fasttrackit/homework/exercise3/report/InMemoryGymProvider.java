package ro.fasttrackit.homework.exercise3.report;

import ro.fasttrackit.homework.exercise3.gym.Gym;
import ro.fasttrackit.homework.exercise3.gym.GymMember;
import ro.fasttrackit.homework.exercise3.gym.Membership;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;

public class InMemoryGymProvider implements GymProvider {
    @Override
    public Gym readGym() {
        return Gym.builder()
                .name("test gym")
                .subscriptions(Set.of(
                        Membership.builder()
                                .gymMember(GymMember.builder()
                                        .name("Beluci")
                                        .birthDate(LocalDate.now().minusYears(69))
                                        .build())
                                .remainingDuration(Duration.of(20, DAYS))
                                .build(),
                        Membership.builder()
                                .gymMember(GymMember.builder()
                                        .name("Markuci")
                                        .birthDate(LocalDate.now().minusYears(44))
                                        .build())
                                .remainingDuration(Duration.of(20, HOURS))
                                .build(),
                        Membership.builder()
                                .gymMember(GymMember.builder()
                                        .name("Gezuci")
                                        .birthDate(LocalDate.now().minusYears(55))
                                        .build())
                                .remainingDuration(Duration.of(7, HOURS))
                                .build(),
                        Membership.builder()
                                .gymMember(GymMember.builder()
                                        .name("Reniti")
                                        .birthDate(LocalDate.now().minusYears(25))
                                        .build())
                                .remainingDuration(Duration.of(3, DAYS))
                                .build()))
                .build();
    }
}
