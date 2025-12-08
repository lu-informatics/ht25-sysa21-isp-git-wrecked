package se.lu.ics.models;

import java.util.ArrayList;

public class VehicleRegister {
    private ArrayList<Vehicle> vehicles;

    public VehicleRegister() {
        this.vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle veicle){
        this.vehicles.add(veicle);
    }

    public Vehicle findVehicle(int vin){
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin() == vin) { //kolla vilken metod för get i vehicle
                return vehicle;
            }
        }
        return null;
    }

    public void removeVehicle(int vin){
        for(Vehicle v : vehicles){
            if (v.getVin()==vin){
                vehicles.remove(v);
            }
        }
    }

    public ArrayList<Vehicle> getAllVeicles(){
        return vehicles;
    }

    public double getTotalServiceCostForAllVehicles(){
        double total= 0.0;

        for(Vehicle v : vehicles){
            if(v!=null){
                total+=v.getTotalCost();
            }
        }
        return total;
    }

    public double calculateAvarageCost(){
        double avarageCost=0.0;

        if (vehicles.size() == 0) {
            return 0.0;
        }
        avarageCost+= getTotalServiceCostForAllVehicles() / vehicles.size();
        return avarageCost;
    }

    public ServiceHistoryEntry getMostExpensiveService(){
        ServiceHistoryEntry mostExpensive = null;

        for(Vehicle v : vehicles){
            for (ServiceHistoryEntry entry : v.getServiceHistory()) {
                if(mostExpensive==null || entry.getCost()> mostExpensive.getCost()){
                    mostExpensive= entry;
                }
            }
        }
        return mostExpensive;
        //under konstruktion :)
    }
}