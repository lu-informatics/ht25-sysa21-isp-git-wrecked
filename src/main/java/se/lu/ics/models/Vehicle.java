package se.lu.ics.models;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Vehicle {
    private int vin;
    private String location;
    private int capacity;
    private boolean isAvailable;
    private String name;
    private VehicleType type;
    private MaintenanceSchedule maintenanceSchedule;
    private ObservableList<ServiceHistoryEntry> serviceHistory;
    private static int counter = 1;

    public Vehicle(String location, int capacity, boolean isAvailable, VehicleType type, String name) {
        this.vin = counter++;
        this.location = location;
        this.capacity = capacity;
        this.isAvailable = isAvailable;
        this.type = type;
        this.name = name;
        serviceHistory = FXCollections.observableArrayList();

        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty");
        }

        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (type == null) {
            throw new IllegalArgumentException("Vehicle type cannot be empty");
        }

    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public int getVin() {
        return vin;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public MaintenanceSchedule getMaintenanceSchedule() {
        return maintenanceSchedule;
    }

    public void addServiceHistoryEntry(ServiceHistoryEntry entry) {
        serviceHistory.add(entry);

        if (getTotalCost() > 100000) {
            throw new IllegalStateException("WARNING - Total cost exceeds 100000");
        }
    }

    public void removeServiceHistoryEntry(ServiceHistoryEntry entry) {
        serviceHistory.remove(entry);
    }

    public void addMaintenanceSchedule(MaintenanceSchedule schedule) {
        this.maintenanceSchedule = schedule;
    }

    public void removeMaintenanceSchedule() { // KOLLA IFALL DETTA VERKLIGEN ÄR RÄTT
        this.maintenanceSchedule = null;
    }

    public ObservableList<ServiceHistoryEntry> getServiceHistory() {
        return FXCollections.unmodifiableObservableList(serviceHistory);
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }

    public int getPartsReplaced() {
        int partsReplaced = 0;
        for (ServiceHistoryEntry entry : serviceHistory) {
            partsReplaced += entry.getPartsReplaced();
        }
        return partsReplaced;
    }

    public double getTotalCost() {
        double totalCost = 0.0;
        for (ServiceHistoryEntry entry : serviceHistory) {
            totalCost += entry.getCost();
        }
        return totalCost;
    }

    public ArrayList<Workshop> listEveryWorkshop() {
        ArrayList<Workshop> workshops = new ArrayList<>();

        for (ServiceHistoryEntry entry : serviceHistory) {
            Workshop w = entry.getWorkshop();
            if (!workshops.contains(w)) {
                workshops.add(w);
            }
        }
        return workshops;
    }

    @Override
    public String toString() {
        return String.valueOf(vin);
    }

}
