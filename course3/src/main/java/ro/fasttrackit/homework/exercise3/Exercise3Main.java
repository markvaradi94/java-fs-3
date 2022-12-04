package ro.fasttrackit.homework.exercise3;

import ro.fasttrackit.homework.exercise3.gym.Gym;
import ro.fasttrackit.homework.exercise3.report.FileGymReportWriter;
import ro.fasttrackit.homework.exercise3.report.GymReportGenerator;
import ro.fasttrackit.homework.exercise3.report.InMemoryGymProvider;

import java.time.Duration;
import java.time.LocalDate;

public class Exercise3Main {
    public static void main(String[] args) {
        var gymProvider = new InMemoryGymProvider();
        var reportWriter = new FileGymReportWriter();
        var reportGenerator = new GymReportGenerator(gymProvider, reportWriter);
        Gym daGym = gymProvider.readGym();

        System.out.println(daGym.oldestMemberAge());
        System.out.println(daGym.youngestMemberAge());
        System.out.println(daGym.averageMemberAge());

        System.out.println(daGym.memberInfo("markuci"));
        System.out.println(daGym.remainingTimeForAll());
        System.out.println(daGym.remainingTimeByMember());
        System.out.println("===================================================");
        reportGenerator.generateReport();

        daGym.registerNewSubscription("Guszti", LocalDate.now().minusYears(19), Duration.ofDays(10));
        daGym.increaseMembershipDurationForMember("reniti", Duration.ofHours(10));
        daGym.registerTimeForMember("beluci", Duration.ofHours(50));
        System.out.println(daGym.remainingTimeByMember());
    }
}
