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
        this.date = date;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

}
