package se.lu.ics.models;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Queue;

public class Workshop {
    private String name;
    private boolean isInternal;
    private String address;
    private ObservableList<ServiceHistoryEntry> services;
    private ObservableList<MaintenanceSchedule> schedules;

    public Workshop(String name, boolean isInternal, String address) {
        this.name = name;
        this.isInternal = isInternal;
        this.address = address;
        services = javafx.collections.FXCollections.observableArrayList();
        schedules = javafx.collections.FXCollections.observableArrayList();
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can not be empty");
        }
        this.name = name;
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
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address can not be empty");
        }
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setServices(ObservableList<ServiceHistoryEntry> services) {
        this.services = services;
    }

    public ObservableList<ServiceHistoryEntry> getServices() {
        return FXCollections.unmodifiableObservableList(services);
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

    
    @Override
    public String toString() {
        return name;
    }

}
