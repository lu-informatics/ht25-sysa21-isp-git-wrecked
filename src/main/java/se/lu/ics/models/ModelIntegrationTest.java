package se.lu.ics.models;

public class ModelIntegrationTest {
    public static void main(String[] args) {
        VehicleRegister vehicleRegister = new VehicleRegister();
        WorkshopRegister workshopRegister = new WorkshopRegister();
        Vehicle vehicle1 = new Vehicle("Lund", 6, "Mediumtruck", "Ferrari");
        Vehicle vehicle2 = new Vehicle("Malmö", 12, "Van", "Lamborghini");
        Vehicle vehicle3 = new Vehicle("Stockholm", 8, "Largetruck", "Porsche");

        Workshop workshop1 = new Workshop("AutoFix", isInternal, "Bilvägen 1");
        Workshop workshop2 = new Workshop("CarCare", isExternal, "Motorvägen 5");

        MaintenanceSchedule schedule1 = new MaintenanceSchedule(LocalDate.of(2024, 7, 15), "Oil Change");
        MaintenanceSchedule schedule2 = new MaintenanceSchedule(LocalDate.of(2024, 8, 20), "Tire Rotation");

        ServiceHistoryEntry entry1 = new ServiceHistoryEntry("Engine noise", 250.0, LocalDate.of(2024, 6, 10));
        ServiceHistoryEntry entry2 = new ServiceHistoryEntry("Brake replacement", 400.0, LocalDate.of(2024, 5, 5));

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

        System.out.println("Vehicles in Register:");
        for (Vehicle v : vehicleRegister.getAllVehicles()) {
            System.out.println(v.getLocation() + " " + v.getCapacity() + " " + v.getType() + " " + v.getName() + "");
        }
        System.out.println("Workshops in Register:");
        for (Workshop w : workshopRegister.getAllWorkshops()) {
            System.out.println(w.getName());
        }
        System.out.println("Service History Entries:");
        for (Vehicle v : vehicleRegister.getAllVehicles()) {
            for (ServiceHistoryEntry e : v.getServiceHistory()) {
                System.out.println(e.getProblemDescription() + " Cost: " + e.getCost() + " Date: " + e.getDate());
            }
        }
        System.out.println("Maintenance Schedules:");
        for (Vehicle v : vehicleRegister.getAllVehicles()) {
            System.out.println(
                    "Mantenance Schedule for vehicles: " + v.getName() + v.getMaintenanceSchedule().getNextServiceDate()
                            + " Description: " + v.getMaintenanceSchedule().getDescription());
        }
        System.out.println("Available Vehicles:");
        for (Vehicle v : vehicleRegister.getAllVehicles()) {
            if (v.isAvailable()) {
                System.out.println(v.getLocation() + " " + v.getCapacity() + " " + v.getType() + " " + v.getName());
            }
        }
        for (Vehicle v : vehicleRegister.getAllVehicles()) {
            System.out.println("Vehicle: " + v.getName() + " is maintained by workshop "
                    + v.getMaintenanceSchedule().getWorkshop().getName());
        }
        for (ServiceHistoryEntry e : vehicle1.getServiceHistory()) {
            System.out.println("Service Entry for vehicle " + vehicle1.getName() + ": " + e.getProblemDescription()
                    + " is carried out by workshop " + workshop1.getName());
        }
        System.out.println("Services activities performed:");
        for (ServiceHistoryEntry e : vehicle1.getServiceHistory()) {
            for (String activity : e.getServiceActivities()) {
                System.out.println(activity);
            }
        }
        System.out.println("Parts replaced during services:");
        for (ServiceHistoryEntry e : vehicle2.getServiceHistory()) {
            for (String part : e.getPartsReplaced()) {
                System.out.println(part);
            }

        }






















































































        

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        System.out.println("The most expensive service is: " + vehicleRegister.getMostExpensiveService().getProblemDescription();
        System.out.println("The vehicle with the highest service cost is:" + vehicleRegister.getMostExpensiveService().getVehicle().getName());
        System.out.println("The workshop that performed the most expensive service is:" + vehicleRegister.getMostExpensiveService().getWorkshop().getName());

        System.out.println("The most expensive workshop is:");
        System.out.println(workshopRegister.getMostExpensiveWorkshop().getName());
        
        System.out.println("Workshops used by vehicle " + vehicle1.getName() + ":");
        for (Workshop tempWorkshop : vehicle1.listEveryWorkshop()) {
            System.out.println(tempWorkshop.getName());
        }
    }
}
