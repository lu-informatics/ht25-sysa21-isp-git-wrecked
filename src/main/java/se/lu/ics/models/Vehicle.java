package se.lu.ics.models;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Vehicle  {
    private int vin;
    private Vehicle vehicle;
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

    public int getVin() {
        return vin;
    }

    public void setLocation(String location) {
        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Location must have letters or numbers");
        }
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
            if (capacity <= 0) {
                throw new IllegalArgumentException("Capacity must be greater than zero");
            }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setName(String name) {
        this.name = name;
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
                }

    public String getName() {
        return name;
    }

    public MaintenanceSchedule getMaintenanceSchedule() {
        return maintenanceSchedule;
    }
    public void setMaintenanceSchedule(MaintenanceSchedule maintenanceSchedule) {
        this.maintenanceSchedule = maintenanceSchedule;
    }

    public void addServiceHistoryEntry(ServiceHistoryEntry entry) {
        serviceHistory.add(entry);

        if (getTotalCost() > 100000) {
            throw new IllegalStateException("WARNING - Total cost exceeds 100000");
        }
    }

    public void removeServiceHistoryEntry(ServiceHistoryEntry entry) {
        if (serviceHistory.isEmpty() || entry == null) {
            throw new IllegalArgumentException("No service history entries to remove.");
        }
        else {
        serviceHistory.remove(entry);
        }

    }


    public void removeMaintenanceSchedule(MaintenanceSchedule schedule) {
        if (this.maintenanceSchedule == null) {
            throw new IllegalArgumentException("No maintenance schedule to remove.");
        }
        this.maintenanceSchedule = null;
    }

    public ObservableList<ServiceHistoryEntry> getServiceHistory() {
        return serviceHistory;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    

    public boolean isAvailable() {
        return isAvailable;
    }
    public String getAvailableString() {
    if (isAvailable) {
        return "Yes";
    } else {
        return "No";
    }
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
 

    

    public double getVehicleTotalServiceCost() {
        double total = 0.0;

        for (ServiceHistoryEntry entry : serviceHistory) {
            total += entry.getCost();
        }
        return total;
    }
}
