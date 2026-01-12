package se.lu.ics.models;

import java.time.LocalDate;

public class MaintenanceSchedule {
    private LocalDate localDate;
    private String description;
    private Workshop workshop;
    private Vehicle vehicle;

    public MaintenanceSchedule(LocalDate date, String description) {
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be empty or in the past");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
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

        if (workshop == null) {
            throw new IllegalArgumentException("Workshop cannot be null");
        }

        if (vehicle != null
            && vehicle.getType() == VehicleType.LARGE_TRUCK
            && workshop.getIsInternal()) {

            throw new IllegalStateException(
            "Large trucks cannot be serviced at internal workshops."
            );
        }

        if (this.workshop == workshop) {
            return;
        }

        if (this.workshop != null) {
            this.workshop.removeSchedule(this);
        }

        this.workshop = workshop;
        workshop.addSchedule(this);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        this.vehicle = vehicle;
}


    public int getVin(){
        return vehicle.getVin();
    }
}
