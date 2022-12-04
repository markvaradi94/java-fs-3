package ro.fasttrackit.homework.exercise3.report;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record GymReportGenerator(
        GymProvider gymProvider,
        GymReportWriter reportWriter
) {
    public void generateReport() {
        var gym = gymProvider.readGym();
        reportWriter.writeReport(gym);
    }
}
