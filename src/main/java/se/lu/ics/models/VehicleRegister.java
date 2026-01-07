package se.lu.ics.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VehicleRegister {
    private ObservableList<Vehicle> vehicles;
    private ServiceHistoryEntry serviceHistory;

    public VehicleRegister() {
        this.vehicles = FXCollections.observableArrayList();
    }


    public void addVehicle(Vehicle vehicle) {
        if (vehicles.contains(vehicle)) {
            throw new IllegalArgumentException("The vehicle already exists.");
        }
        this.vehicles.add(vehicle);
    }

    public Vehicle findVehicle(int vin) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin() == vin) {
                return vehicle;
            }
        }
        throw new IllegalArgumentException("Vehicle not found.");
    }

    public void removeVehicle(int vin) {
        Vehicle toRemove = null;

        for (Vehicle v : vehicles) {
            if (v.getVin() == vin) {
                toRemove = v;
                break;
            }
        }

        if (toRemove != null) {
            vehicles.remove(toRemove);
        } else {
            throw new IllegalArgumentException("Vehicle not found.");
        }
    }

    public ObservableList<Vehicle> getVehicles() {
        return FXCollections.unmodifiableObservableList(this.vehicles);
    }


    public String printAllMaintenanceSchedules() {
        if (vehicles.isEmpty()) {
            throw new IllegalArgumentException("No vehicles in the register.");
        }
        String result = " ";

        for (Vehicle v : vehicles) {
            MaintenanceSchedule m = v.getMaintenanceSchedule();
            if (m == null) {
                throw new IllegalArgumentException("No maintenance schedule found for vehicle VIN: " + v.getVin());
            } else {
                result += v.getVin() + " " + m.getDate() + " " + m.getDescription();
            }
        }
        return result;
    }

    public double getTotalServiceCostForAllVehicles() {
        double total = 0.0;

        for (Vehicle v : vehicles) {
            if (v != null) {
                total += v.getTotalCost();
            }
        }
        return total;
    }

    public double calculateAvarageCost() {
        double avarageCost = 0.0;

        if (vehicles.size() == 0) {
            return 0.0;
        }
        avarageCost += getTotalServiceCostForAllVehicles() / vehicles.size();
        return avarageCost;
    }

    public ServiceHistoryEntry getMostExpensiveService() {
        ServiceHistoryEntry mostExpensive = null;

        for (Vehicle v : vehicles) {
            for (ServiceHistoryEntry entry : v.getServiceHistory()) {
                if (mostExpensive == null || entry.getCost() > mostExpensive.getCost()) {
                    mostExpensive = entry;
                }
            }
        }
        return mostExpensive;
    }
    public Vehicle getVehicleForServiceEntry(ServiceHistoryEntry entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Service history entry cannot be null.");
        }
        for (Vehicle v : vehicles) {
            if (v.getServiceHistory().contains(entry)) {
                return v;
            }
        }
        return null;
    }
}