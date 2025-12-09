package se.lu.ics.models;

import java.time.LocalDate;

public class ModelIntegrationTest {
    public static void main(String[] args) {
        VehicleRegister vehicleRegister = new VehicleRegister();
        WorkshopRegister workshopRegister = new WorkshopRegister();
        Vehicle vehicle1 = new Vehicle("Lund", 6, VehicleType.MEDIUM_TRUCK, "Ferrari");
        Vehicle vehicle2 = new Vehicle("Malmö", 12, VehicleType.VAN, "Lamborghini");
        Vehicle vehicle3 = new Vehicle("Stockholm",8, VehicleType.LARGE_TRUCK, "Porsche");

        Workshop workshop1 = new Workshop("AutoFix",true, "Bilvägen 1");
        Workshop workshop2 = new Workshop("CarCare", false, "Motorvägen 5");

        MaintenanceSchedule schedule1 = new MaintenanceSchedule(LocalDate.of(2024, 7, 15), "Oil Change");
        MaintenanceSchedule schedule2 = new MaintenanceSchedule(LocalDate.of(2024, 8, 20), "Tire Rotation");

        ServiceHistoryEntry entry1 = new ServiceHistoryEntry("Brake issues", 250.0, LocalDate.of(2024, 6, 10));
        ServiceHistoryEntry entry2 = new ServiceHistoryEntry("Engine noise", 400.0, LocalDate.of(2024, 5, 5));

        vehicleRegister.addVehicle(vehicle1);
        vehicleRegister.addVehicle(vehicle2);
        vehicleRegister.addVehicle(vehicle3);

        workshopRegister.addWorkshop(workshop1);
        workshopRegister.addWorkshop(workshop2);

        vehicle1.addMaintenanceSchedule(schedule1);
        vehicle2.addMaintenanceSchedule(schedule2);

        vehicle1.addServiceHistoryEntry(entry1);
        vehicle2.addServiceHistoryEntry(entry2);

        workshop1.addSchedule(schedule1);
        workshop2.addSchedule(schedule2);

        entry1.addServiceActivity("Replace brake pads");
        entry1.addServiceActivity("Cleaned brake system");
        entry2.addServiceActivity("Replaced engine oil");
 
        System.out.println("Vehicles in Register:");
        for (Vehicle v : vehicleRegister.getAllVehicles()) { 
            System.out.println(v.getLocation() + " " + v.getCapacity() + " " +  v.getType() + " " + v.getName() + "");   
        }
        System.out.println("Workshops in Register:");
        for (Workshop w : workshopRegister.getWorkshops()) {
            System.out.println(w.getName());
        }
        System.out.println("Service History Entries for all vehicles:");
        for (Vehicle ve : vehicleRegister.getAllVehicles()) {
            for (ServiceHistoryEntry entry : ve.getServiceHistory()) {
                System.out.println(entry.getProblemDescription() + " Cost: " + entry.getCost() + " Date: " + entry.getDate());
            }
        }
        System.out.println("Service History Entries for vehicle " + vehicle1.getName() + ":");
        for (ServiceHistoryEntry e : vehicle1.getServiceHistory()) {
            System.out.println(e.getProblemDescription() + " Cost: " + e.getCost() + " Date: " + e.getDate());
        }
        System.out.println("Maintenance Schedules:");
        for (Vehicle veh: vehicleRegister.getAllVehicles()){
            System.out.println("Mantenance Schedule for vehicles: " + veh.getName()+ vehicleRegister.getMaintenanceSchedule().getNextServiceDate() + " Description: " + vehicleRegister.getMaintenanceSchedule().getDescription());
        }
        System.out.println("Available Vehicles:");
       for (Vehicle vehi : vehicleRegister.getAllVehicles()) {
            if (vehi.isAvailable()) {
            System.out.println(vehi.getLocation() + " " + vehi.getCapacity() + " " +  vehi.getType() + " " + vehi.getName() );
            }
        }
        System.out.println("Workshop that carried out maintenance:");
        for (Vehicle vehic : vehicleRegister.getAllVehicles()) {
            System.out.println("Vehicle: " + vehic.getName() + " is maintained by workshop " + vehic.getMaintenanceSchedule().getWorkshop().getName());
        }
        System.out.println("Workshop that carried out service entries:");
        for (ServiceHistoryEntry en: vehicle1.getServiceHistory()) {
            System.out.println("Service Entry for vehicle " + vehicle1.getName() + ": " + en.getProblemDescription() + " is carried out by workshop " + workshop1.getName());
        }
        System.out.println("Services activities performed:");
        for (ServiceHistoryEntry ent : vehicle1.getServiceHistory()) {
            for (String activity : ent.getServiceActivities()) {
                System.out.println(activity);
            }
        }
        System.out.println("Parts replaced during services:");
        for (ServiceHistoryEntry entr: vehicle2.getServiceHistory()) {
                System.out.println(entr.getPartsReplaced());
        }
        System.out.println("Service history for workshop " + workshop1.getName() + ":");
        for (ServiceHistoryEntry entry : workshop1.getServices()) {
            System.out.println(entry.getProblemDescription() + " for vehicle " + vehicle1.getName());
        }
        System.out.println("Total cost of services for vehicle " + vehicle2.getName() + ": " + vehicle2.getTotalCost());
        
        System.out.println("Total cost of services for all vehicles:");
        for (Vehicle vehicl : vehicleRegister.getAllVehicles()) {
            System.out.println("Vehicle " + vehicl.getName() + ": " + vehicl.getTotalCost());
        }
        double averageCost = vehicleRegister.calculateAvarageCost();
        System.out.println("Average cost of vehicle maintenance:" + averageCost);

        vehicleRegister.removeVehicle(vehicle3.getVin());
        System.out.println("Vehicles after removal:" + vehicleRegister.getAllVehicles());

        workshopRegister.removeWorkshop(workshop2.getName());
        System.out.println("Workshops after removal:" + workshopRegister.getWorkshops());

        vehicle1.removeServiceHistoryEntry(entry1);
        System.out.println("Service history after removal:" + vehicle1.getServiceHistory());

        vehicle2.removeMaintenanceSchedule();
        System.out.println("Maintenance schedule after removal:" + vehicle2.getMaintenanceSchedule());
    }
}
