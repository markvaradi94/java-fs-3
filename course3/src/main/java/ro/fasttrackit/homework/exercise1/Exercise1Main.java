package ro.fasttrackit.homework.exercise1;

import ro.fasttrackit.homework.exercise1.composition.FilePeopleProvider;
import ro.fasttrackit.homework.exercise1.composition.FilePeopleReportWriter;
import ro.fasttrackit.homework.exercise1.composition.InMemoryPeopleProvider;
import ro.fasttrackit.homework.exercise1.composition.PeopleReportGenerator;
import ro.fasttrackit.homework.exercise1.inheritance.FilePeopleReportGenerator;
import ro.fasttrackit.homework.exercise1.inheritance.InMemoryPeopleReportGenerator;

public class Exercise1Main {
    public static void main(String[] args) {
        var fileGenerator1 = new FilePeopleReportGenerator("src/main/resources/people.txt");
        fileGenerator1.generateReport("src/main/resources/output/people-inh-1");

        var inMemoryGenerator1 = new InMemoryPeopleReportGenerator();
        inMemoryGenerator1.generateReport("src/main/resources/output/people-inh-2");

        var fileGenerator2 = PeopleReportGenerator.builder()
                .peopleProvider(FilePeopleProvider.builder()
                        .sourceFile("src/main/resources/people.txt")
                        .build())
                .reportWriter(FilePeopleReportWriter.builder()
                        .outputFile("src/main/resources/output/people-comp-1")
                        .build())
                .build();
        fileGenerator2.generateReport();

        var fileGenerator3 = PeopleReportGenerator.builder()
                .peopleProvider(InMemoryPeopleProvider.builder().build())
                .reportWriter(FilePeopleReportWriter.builder()
                        .outputFile("src/main/resources/output/people-comp-2")
                        .build())
                .build();
        fileGenerator3.generateReport();
    }
}
