package ro.fasttrackit.homework.exercise3.gym;

import lombok.Builder;
import lombok.With;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.time.LocalDate.now;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.*;
import static ro.fasttrackit.homework.exercise3.gym.MembershipCategory.determineCategory;

@With
@Builder
public record Gym(
        String name,
        Set<Membership> subscriptions
) {
    public Gym {
        subscriptions = ofNullable(subscriptions)
                .map(HashSet::new)
                .orElseGet(HashSet::new);
    }

    public Map<MembershipCategory, List<String>> generateMembershipReport() {
        return subscriptions.stream()
                .collect(groupingBy(this::getCategoryForMembership, TreeMap::new, mapping(this::membershipOwner, toList())));
    }

    private MembershipCategory getCategoryForMembership(Membership membership) {
        return determineCategory(membership.remainingDuration());
    }

    public String remainingTimeForAll() {
        long totalRemainingHours = subscriptions.stream()
                .map(Membership::remainingDuration)
                .map(Duration::toHours)
                .flatMapToLong(LongStream::of)
                .sum();

        return readableHours(totalRemainingHours);
    }

    public Map<String, String> remainingTimeByMember() {
        return subscriptions.stream()
                .collect(toMap(this::membershipOwner, this::mapMembershipDurationToHours));
    }

    private String mapMembershipDurationToHours(Membership membership) {
        return ofNullable(membership.remainingDuration())
                .map(Duration::toHours)
                .map(Gym::readableHours)
                .orElseThrow(() -> new RuntimeException("Could not convert duration to hours."));
    }

    public MemberInfo memberInfo(String memberName) {
        return subscriptions.stream()
                .filter(membership -> isCorrectMember(memberName, membership))
                .findFirst()
                .map(this::mapToMemberInfo)
                .orElseThrow(() -> new RuntimeException("Could not find member with name: " + memberName));
    }

    private MemberInfo mapToMemberInfo(Membership membership) {
        MemberInfo memberInfo = MemberInfo.builder()
                .remainingMembership(mapMembershipDurationToHours(membership))
                .build();
        return ofNullable(membership.gymMember())
                .map(gymMember -> memberInfo.toBuilder()
                        .ownerName(gymMember.name())
                        .birthDate(gymMember.birthDate())
                        .build())
                .orElse(memberInfo);
    }

    public int oldestMemberAge() {
        return subscriptions.stream()
                .map(Membership::gymMember)
                .map(GymMember::birthDate)
                .map(this::mapToYears)
                .flatMapToInt(IntStream::of)
                .max()
                .orElseThrow(() -> new RuntimeException("Could not calculate highest age correctly."));
    }

    public int youngestMemberAge() {
        return subscriptions.stream()
                .map(Membership::gymMember)
                .map(GymMember::birthDate)
                .map(this::mapToYears)
                .flatMapToInt(IntStream::of)
                .min()
                .orElseThrow(() -> new RuntimeException("Could not calculate lowest age correctly."));
    }

    public Double averageMemberAge() {
        return subscriptions.stream()
                .map(Membership::gymMember)
                .map(GymMember::birthDate)
                .map(this::mapToYears)
                .flatMapToInt(IntStream::of)
                .average()
                .orElseThrow(() -> new RuntimeException("Could not calculate average correctly."));
    }

    private int mapToYears(LocalDate birthDate) {
        return Optional.of(birthDate)
                .map(date -> date.until(now()))
                .map(Period::getYears)
                .orElseThrow(() -> new RuntimeException("Could not map age correctly for birthdate: " + birthDate));
    }

    public void registerNewSubscription(String name, LocalDate birthDate, Duration duration) {
        subscriptions.add(Membership.builder()
                .gymMember(GymMember.builder()
                        .name(name)
                        .birthDate(birthDate)
                        .build())
                .remainingDuration(duration)
                .build());
    }

    public void increaseMembershipDurationForMember(String name, Duration duration) {
        Membership modifiedMembership = subscriptions.stream()
                .filter(membership -> isCorrectMember(name, membership))
                .findFirst()
                .map(membership -> membership.toBuilder()
                        .remainingDuration(membership.remainingDuration().plus(duration))
                        .build())
                .orElseThrow(() -> new RuntimeException("Could not find membership for member: " + name));

        updateSubscriptions(name, modifiedMembership);
    }

    public void registerTimeForMember(String name, Duration duration) {
        Membership modifiedMembership = subscriptions.stream()
                .filter(membership -> isCorrectMember(name, membership))
                .findFirst()
                .map(membership -> membership.toBuilder()
                        .remainingDuration(membership.remainingDuration().minus(duration))
                        .build())
                .orElseThrow(() -> new RuntimeException("Could not find membership for member: " + name));

        updateSubscriptions(name, modifiedMembership);
    }

    private static String readableHours(long hours) {
        return hours + " hours";
    }

    private void updateSubscriptions(String name, Membership modifiedMembership) {
        Membership currentMembership = findMembershipForUser(name);
        subscriptions.remove(currentMembership);
        subscriptions.add(modifiedMembership);
    }

    private Membership findMembershipForUser(String name) {
        return subscriptions.stream()
                .filter(membership -> isCorrectMember(name, membership))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find membership for member: " + name));
    }

    private String membershipOwner(Membership membership) {
        return Optional.ofNullable(membership.gymMember())
                .map(GymMember::name)
                .orElseThrow(() -> new RuntimeException("Could not extract member name from membership."));
    }

    private boolean isCorrectMember(String name, Membership membership) {
        return ofNullable(membership.gymMember())
                .map(GymMember::name)
                .map(name::equalsIgnoreCase)
                .orElseThrow(() -> new RuntimeException("Could not find member with name " + name));
    }
}
