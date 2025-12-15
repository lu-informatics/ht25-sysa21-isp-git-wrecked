package se.lu.ics.models;

import java.util.ArrayList;
import java.util.Queue;

public class Workshop {
    private String name;
    private boolean isInternal;
    private String address;
    private ArrayList<ServiceHistoryEntry> services;
    private ArrayList<MaintenanceSchedule> schedules;

    public Workshop(String name, boolean isInternal, String address) {
        this.name = name;
        this.isInternal = isInternal;
        this.address = address;
        services = new ArrayList<>();
        schedules = new ArrayList<>();
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
        isInternal = true;
    }

    public boolean getIsInternal() {
        return isInternal;
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

    public void setServices(ArrayList<ServiceHistoryEntry> services) {
        this.services = services;
    }

    public ArrayList<ServiceHistoryEntry> getServices() {
        return services;
    }

    public void setSchedules(ArrayList<MaintenanceSchedule> schedules) {
        this.schedules = schedules;
    }

    public ArrayList<MaintenanceSchedule> getSchedules() {
        return schedules;
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

}
