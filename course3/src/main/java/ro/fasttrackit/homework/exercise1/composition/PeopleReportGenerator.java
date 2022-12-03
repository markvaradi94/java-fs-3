package ro.fasttrackit.homework.exercise1.composition;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record PeopleReportGenerator(
        PeopleProvider peopleProvider,
        PeopleReportWriter reportWriter
) {

    public void generateReport() {
        var people = peopleProvider.readPeople();
        reportWriter.writeReport(people);
    }
}
