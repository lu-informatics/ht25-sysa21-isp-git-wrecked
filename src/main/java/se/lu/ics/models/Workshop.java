package se.lu.ics.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Workshop {
    private String name;
    private boolean isInternal;
    private String address;
    private ObservableList<ServiceHistoryEntry> services;
    private ObservableList<MaintenanceSchedule> schedules;

    public Workshop(String name, boolean isInternal, String address) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Workshop name cannot be empty.");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Workshop address cannot be empty.");
        }

        this.name = name.trim();
        this.isInternal = isInternal;
        this.address = address.trim();
        services = javafx.collections.FXCollections.observableArrayList();
        schedules = javafx.collections.FXCollections.observableArrayList();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Workshop name cannot be empty.");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public void setIsInternal(boolean isInternal) {
        this.isInternal = isInternal;
    }

    public boolean getIsInternal() {
        return isInternal;
    }

    public String getType() {
        return isInternal ? "Internal" : "External";
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Workshop address cannot be empty.");
        }
        this.address = address.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setServices(ObservableList<ServiceHistoryEntry> services) {
        this.services = services;
    }

    public ObservableList<ServiceHistoryEntry> getServices() {
        return services;
    }

    public ObservableList<MaintenanceSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ObservableList<MaintenanceSchedule> schedules) {
        this.schedules = schedules;
    }

    public void addService(ServiceHistoryEntry service) {
        this.services.add(service);
    }

    public void addSchedule(MaintenanceSchedule schedule) {
        this.schedules.add(schedule);
    }

    public int getServiceCount() {
        return services.size();
    }
    
    public void removeService(ServiceHistoryEntry service) {
        this.services.remove(service);
    }
    
    @Override
    public String toString() {
        return name;
    }

}
