package se.lu.ics.models;
import java.util.ArrayList;

public class Vehicle {
    private int vin;
    private String location;
    private int capacity;
    private boolean isAvailable;
    private String name;
    private VehicleType type;
    private MaintenanceSchedule maintenanceSchedule;
    private VehicleRegister vehicleRegister;
    private ArrayList <ServiceHistoryEntry> serviceHistory;
    private static int counter = 1;

    public Vehicle (String location, int capacity, VehicleType type, String name) {
        this.vin = counter++;
        this.location = location;
        this.capacity = capacity;
        this.isAvailable = true;
        this.type = type;
        this.name = name;
        serviceHistory = new ArrayList<>();
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
     public void addServiceHistoryEntry(ServiceHistoryEntry entry) {
        serviceHistory.add(entry);
    }
    public void removeServiceHistoryEntry(ServiceHistoryEntry entry) {
        serviceHistory.remove(entry);
    }
    public void addMaintenanceSchedule(MaintenanceSchedule schedule) {
        this.maintenanceSchedule = schedule;
    }
    public void removeMaintenanceSchedule() { //KOLLA IFALL DETTA VERKLIGEN ÄR RÄTT
        this.maintenanceSchedule = null;
    }
    public ArrayList<ServiceHistoryEntry> getServiceHistory() {
        return serviceHistory;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setType (VehicleType type) {
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


}

