package se.lu.ics.models;

import java.time.LocalDate;

public class MaintenanceSchedule {
    private LocalDate localDate;
    private String description;
    private Workshop workshop;
    private Vehicle vehicle;

    public MaintenanceSchedule(LocalDate date, String description) {
        this.localDate = date;
        this.description = description;
    }

    public void setDate(LocalDate date) {
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be empty or in the past");
        }
        this.localDate = date;
    }

    public LocalDate getDate() {
        return localDate;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
