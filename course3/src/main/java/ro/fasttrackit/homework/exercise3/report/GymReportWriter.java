package ro.fasttrackit.homework.exercise3.report;

import ro.fasttrackit.homework.exercise3.gym.Gym;
import ro.fasttrackit.homework.exercise3.gym.MembershipCategory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface GymReportWriter {
    void writeReport(Gym gym);

    default void writeLine(BufferedWriter writer, Map.Entry<MembershipCategory, List<String>> entry) {
        try {
            writer.write(entry.getKey() + ": " + entry.getValue());
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
