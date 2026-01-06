package se.lu.ics.models;


import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class ServiceHistoryEntry {
    private String problemDescription;
    private double cost;
    private int partsReplaced;
    private LocalDate localDate;
    private ObservableList<String> serviceActivities;
    private Workshop workshop;
    private Vehicle vehicle;

    public ServiceHistoryEntry() {
        this.serviceActivities = FXCollections.observableArrayList();
    }

    public ServiceHistoryEntry(String problemDescription, double cost, LocalDate serviceDate, int partsReplaced) {

        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }

        if (problemDescription == null || problemDescription.isEmpty()) {
            throw new IllegalArgumentException("Problem description cannot be empty");
        }
        if (serviceDate == null) {
            throw new IllegalArgumentException("Date cannot be empty");
        }

        this.problemDescription = problemDescription;
        this.cost = cost;
        this.localDate = serviceDate;
        this.partsReplaced = partsReplaced;
        this.serviceActivities = FXCollections.observableArrayList();
    }

    public void addServiceActivity(String activity) {
        if (activity == null || activity.isEmpty()) {
            throw new IllegalArgumentException("Activity cannot be empty");
        }
        this.serviceActivities.add(activity);
    }

    public void removeServiceActivity(String activity) {

        if (activity == null || activity.isEmpty()) {
            throw new IllegalArgumentException("Activity cannot be empty");
        }
        this.serviceActivities.remove(activity);
    }

    public ObservableList<String> getServiceActivities() {
        return serviceActivities;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        if (problemDescription == null || problemDescription.isEmpty()) {
            throw new IllegalArgumentException("Problem description cannot be empty");
        }
        this.problemDescription = problemDescription;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }
        this.cost = cost;
    }

    public LocalDate getDate() {
        return localDate;
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be empty");
        }
        this.localDate = date;
    }

    public int getPartsReplaced() {
        return partsReplaced;
    }

    public void setPartsReplaced(int partsReplaced) {
        this.partsReplaced = partsReplaced;
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
