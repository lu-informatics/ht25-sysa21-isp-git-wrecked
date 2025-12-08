package se.lu.ics.models;
import java.time.LocalDate;

public class MaintenanceSchedule {
    private LocalDate date;
    private String description;

    public MaintenanceSchedule(LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }

    public void setDate(LocalDate date) {
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be empty or in the past");
        }
        this.date = date;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDescription(String description) {
        if (description ==null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
