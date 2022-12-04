package ro.fasttrackit.homework.exercise3.gym;

import java.time.Duration;

public enum MembershipCategory {
    RED,
    YELLOW,
    GREEN;

    public static MembershipCategory determineCategory(Duration duration) {
        long hours = duration.toHours();
        if (hours < 10) {
            return RED;
        } else if (hours < 30) {
            return YELLOW;
        } else {
            return GREEN;
        }
    }
}
