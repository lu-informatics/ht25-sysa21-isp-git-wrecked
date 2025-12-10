package se.lu.ics.models;

import java.time.LocalDate;

public class ModelIntegrationTest {
    public static void main(String[] args) {
        VehicleRegister vehicleRegister = new VehicleRegister();
        WorkshopRegister workshopRegister = new WorkshopRegister();
        Vehicle vehicle1 = new Vehicle("Lund", 6, VehicleType.MEDIUM_TRUCK, "Ferrari");
        Vehicle vehicle2 = new Vehicle("Malmö", 12, VehicleType.VAN, "Lamborghini");
        Vehicle vehicle3 = new Vehicle("Stockholm", 8, VehicleType.LARGE_TRUCK, "Porsche");

        Workshop workshop1 = new Workshop("AutoFix", true, "Bilvägen 1");
        Workshop workshop2 = new Workshop("CarCare", false, "Motorvägen 5");

        MaintenanceSchedule schedule1 = new MaintenanceSchedule(LocalDate.of(2024, 7, 15), "Oil Change");
        MaintenanceSchedule schedule2 = new MaintenanceSchedule(LocalDate.of(2024, 8, 20), "Tire Rotation");
        MaintenanceSchedule schedule3 = new MaintenanceSchedule(LocalDate.of(2024, 9, 25), "Brake Inspection");

        ServiceHistoryEntry entry1 = new ServiceHistoryEntry("Brake issues", 250.0, LocalDate.of(2024, 6, 10), 1);
        ServiceHistoryEntry entry2 = new ServiceHistoryEntry("Engine noise", 400.0, LocalDate.of(2024, 5, 5), 2);
        ServiceHistoryEntry entry3 = new ServiceHistoryEntry("Flat tire", 100.0, LocalDate.of(2024, 4, 15), 1);

        vehicleRegister.addVehicle(vehicle1);
        vehicleRegister.addVehicle(vehicle2);
        vehicleRegister.addVehicle(vehicle3);

        workshopRegister.addWorkshop(workshop1);
        workshopRegister.addWorkshop(workshop2);

        vehicle1.addMaintenanceSchedule(schedule1);
        vehicle2.addMaintenanceSchedule(schedule2);
        vehicle3.addMaintenanceSchedule(schedule3);

        vehicle1.addServiceHistoryEntry(entry1);
        vehicle1.addServiceHistoryEntry(entry2);
        vehicle2.addServiceHistoryEntry(entry2);
        vehicle3.addServiceHistoryEntry(entry3);

        workshop1.addSchedule(schedule1);
        workshop2.addSchedule(schedule2);

        workshop1.addService(entry1);
        workshop2.addService(entry2);
        workshop1.addService(entry3);

        schedule1.setWorkshop(workshop1);
        schedule2.setWorkshop(workshop2);
        schedule3.setWorkshop(workshop1);

        entry1.addServiceActivity("Replace brake pads");
        entry1.addServiceActivity("Cleaned brake system");
        entry2.addServiceActivity("Replaced engine oil");
        entry3.addServiceActivity("Replaced tire");

        entry1.setVehicle(vehicle1);
        entry2.setVehicle(vehicle2);
        entry3.setVehicle(vehicle3);

        entry1.setWorkshop(workshop1);
        entry2.setWorkshop(workshop2);
        entry3.setWorkshop(workshop1);

        System.out.println("Vehicles in Register:");
        for (Vehicle v : vehicleRegister.getAllVehicles()) {
            System.out.println(v.getLocation() + " " + v.getCapacity() + " " + v.getType() + " " + v.getName() + "");
        }
        System.out.println("Workshops in Register:");
        for (Workshop w : workshopRegister.getWorkshops()) {
            System.out.println(w.getName());
        }
        System.out.println("Service History Entries for all vehicles:");
        for (Vehicle ve : vehicleRegister.getAllVehicles()) {
            for (ServiceHistoryEntry entry : ve.getServiceHistory()) {
                System.out.println(
                        entry.getProblemDescription() + " Cost: " + entry.getCost() + " Date: " + entry.getDate());
            }
        }
        System.out.println("Service History Entries for vehicle " + vehicle1.getName() + ":");
        for (ServiceHistoryEntry e : vehicle1.getServiceHistory()) {
            System.out.println(e.getProblemDescription() + " Cost: " + e.getCost() + " Date: " + e.getDate());
        }

        System.out.println("Maintenance Schedules:");
        for (Vehicle veh : vehicleRegister.getAllVehicles()) {
            System.out.println(
                    "Mantenance Schedule for vehicles: " + veh.getName() + " " + veh.getMaintenanceSchedule().getDate()
                            + " " + " Description: " + veh.getMaintenanceSchedule().getDescription());
        }
        System.out.println("Available Vehicles:");
        for (Vehicle vehi : vehicleRegister.getAllVehicles()) {
            if (vehi.isAvailable()) {
                System.out.println(
                        vehi.getLocation() + " " + vehi.getCapacity() + " " + vehi.getType() + " " + vehi.getName());
            }
        }
        System.out.println("Workshop that carried out maintenance:");
        for (Vehicle vehic : vehicleRegister.getAllVehicles()) {
            System.out.println("Vehicle:" + vehic.getName() + "is maintained by workshop "
                    + vehic.getMaintenanceSchedule().getWorkshop().getName());
        }
        System.out.println("Workshop that carried out service entries:");
        for (ServiceHistoryEntry en : vehicle1.getServiceHistory()) {
            System.out.println("Service Entry for vehicle " + vehicle1.getName() + ": " + en.getProblemDescription()
                    + " is carried out by workshop " + workshop1.getName());
        }
        System.out.println("Services activities performed:");
        for (ServiceHistoryEntry ent : vehicle1.getServiceHistory()) {
            for (String activity : ent.getServiceActivities()) {
                System.out.println(activity);
            }
        }
        System.out.println("Parts replaced during services:");
        for (ServiceHistoryEntry entr : vehicle2.getServiceHistory()) {
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
        for (Vehicle v : vehicleRegister.getAllVehicles()) {
            System.out.println("Remaining vehicle: " + v.getName());
        }
        workshopRegister.removeWorkshop(workshop2.getName());
        for (Workshop w : workshopRegister.getWorkshops()) {
            System.out.println("Remaining workshop: " + w.getName());
        }

        vehicle1.removeServiceHistoryEntry(entry1);
        for (ServiceHistoryEntry e : vehicle1.getServiceHistory()) {
            System.out.println(
                    "Remaining service entry for vehicle " + vehicle1.getName() + ": " + e.getProblemDescription());
        }
        vehicle2.removeMaintenanceSchedule();
        System.out.println("Maintenance schedule after removal:" + vehicle2.getMaintenanceSchedule());

        System.out.println(
                "The most expensive service is: " + vehicleRegister.getMostExpensiveService().getProblemDescription());
        System.out.println("The vehicle with the highest service cost is:"
                + vehicleRegister.getMostExpensiveService().getVehicle().getName());
        System.out.println("The workshop that performed the most expensive service is:"
                + vehicleRegister.getMostExpensiveService().getWorkshop().getName());

        System.out.println("The most expensive workshop is:");
        System.out.println(workshopRegister.getMostExpensiveWorkshop().getName());

        System.out.println("Workshops used by vehicle " + vehicle1.getName() + ":");

        for (Workshop tempWorkshop : vehicle1.listEveryWorkshop()) {
            System.out.println(tempWorkshop.getName());
        }

    }
}
