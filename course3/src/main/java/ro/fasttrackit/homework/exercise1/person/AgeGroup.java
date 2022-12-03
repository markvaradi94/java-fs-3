package ro.fasttrackit.homework.exercise1.person;

public enum AgeGroup {
    YOUNG("1-30"),
    ADULT("30-50"),
    MATURE("50+");

    private final String description;

    AgeGroup(String description) {
        this.description = description;
    }

    public static String categorizeAge(int age) {
        return determineGroup(age).description;
    }

    private static AgeGroup determineGroup(int age) {
        if (age < 0 || age > 150) {
            throw new RuntimeException("Invalid age value: " + age);
        } else if (age < 30) {
            return YOUNG;
        } else if (age < 50) {
            return ADULT;
        } else {
            return MATURE;
        }
    }

    public String description() {
        return description;
    }
}
