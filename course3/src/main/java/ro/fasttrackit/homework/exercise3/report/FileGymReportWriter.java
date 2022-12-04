package ro.fasttrackit.homework.exercise3.report;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ro.fasttrackit.homework.exercise3.gym.Gym;
import ro.fasttrackit.homework.exercise3.gym.MembershipCategory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class FileGymReportWriter implements GymReportWriter {
    private final String outputFile;

    public FileGymReportWriter() {
        outputFile = "src/main/resources/output/remaining-time-report-" + LocalDate.now() + ".txt";
    }

    @Override
    public void writeReport(Gym gym) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            Map<MembershipCategory, List<String>> report = ofNullable(gym.generateMembershipReport())
                    .orElseThrow(() -> new RuntimeException("Could not fetch membership report for gym: " + gym.name()));
            report.entrySet()
                    .forEach(entry -> writeLine(writer, entry));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

