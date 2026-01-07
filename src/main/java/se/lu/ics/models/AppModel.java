package se.lu.ics.models;

import java.time.LocalDate;

public class AppModel {
   private VehicleRegister vehicleRegister;
   private WorkshopRegister workshopRegister;

   public AppModel() {
      this.vehicleRegister = new VehicleRegister();
      this.workshopRegister = new WorkshopRegister();

      addTestData();
   }

   public VehicleRegister getVehicleRegister() {
      return vehicleRegister;
   }

   public WorkshopRegister getWorkshopRegister() {
      return workshopRegister;
   }

   private void addTestData() {

      Vehicle vehicle1 = new Vehicle("Malmö", 1200, true, VehicleType.MEDIUM_TRUCK, "Volvo");
      Vehicle vehicle2 = new Vehicle("Göteborg", 2200, false, VehicleType.LARGE_TRUCK, "Scania");
      Vehicle vehicle3 = new Vehicle("Lund", 700, true, VehicleType.VAN, "Ford");
      Vehicle vehicle4 = new Vehicle("Helsingborg", 2000, true, VehicleType.LARGE_TRUCK, "Mercedes");
      Vehicle vehicle5 = new Vehicle("Uppsala", 1500, false, VehicleType.MEDIUM_TRUCK, "SAAB");
      Vehicle vehicle6 = new Vehicle("Västerås", 800, false, VehicleType.VAN, "Toyota");
      Vehicle vehicle7 = new Vehicle("Örebro", 1300,true, VehicleType.MEDIUM_TRUCK, "Nissan");
      Vehicle vehicle8 = new Vehicle("Stockholm", 850, true, VehicleType.VAN, "Mercedes Sprinter");
      Vehicle vehicle9 = new Vehicle("Jönköping", 1400, false, VehicleType.MEDIUM_TRUCK, "Scania P280");
      Vehicle vehicle10 = new Vehicle("Linköping", 2300, true, VehicleType.LARGE_TRUCK, "Volvo FH16");

      Workshop workshop1 = new Workshop("Workshop 1", true, "Address 1");
      Workshop workshop2 = new Workshop("Workshop 2", false, "Address 2");
      Workshop workshop3 = new Workshop("Workshop 3", true, "Address 3");
      Workshop workshop4 = new Workshop("Workshop 4", true, "Address 4");

      vehicleRegister.addVehicle(vehicle1);
      vehicleRegister.addVehicle(vehicle2);
      vehicleRegister.addVehicle(vehicle3);
      vehicleRegister.addVehicle(vehicle4);
      vehicleRegister.addVehicle(vehicle5);
      vehicleRegister.addVehicle(vehicle6);
      vehicleRegister.addVehicle(vehicle7);
      vehicleRegister.addVehicle(vehicle8);
      vehicleRegister.addVehicle(vehicle9);
      vehicleRegister.addVehicle(vehicle10);

      workshopRegister.addWorkshop(workshop1);
      workshopRegister.addWorkshop(workshop2);
      workshopRegister.addWorkshop(workshop3);
      workshopRegister.addWorkshop(workshop4);

      ServiceHistoryEntry entry1 = new ServiceHistoryEntry("Brake issues", 250.0, LocalDate.of(2024, 6, 10), 1);
      ServiceHistoryEntry entry2 = new ServiceHistoryEntry("Engine noise", 400.0, LocalDate.of(2024, 5, 5), 2);
      ServiceHistoryEntry entry3 = new ServiceHistoryEntry("Flat tire", 100.0, LocalDate.of(2024, 4, 15), 1);
      
      vehicle1.addServiceHistoryEntry(entry1);
      vehicle1.addServiceHistoryEntry(entry2);
      vehicle1.addServiceHistoryEntry(entry3);

      workshop1.addService(entry1);
      workshop2.addService(entry2);
      workshop3.addService(entry3);

      // Skapar service activities och stoppar dem listan som tillhör Service History Entry 1
      String serviceActivities1 = "Bytt bromsar";
      String serviceActivities2 = "Fixat motorljud";
      String serviceActivities3 = "Bytt däck";

      entry1.addServiceActivity(serviceActivities1);
      entry1.addServiceActivity(serviceActivities2);
      entry2.addServiceActivity(serviceActivities3);

      entry1.setWorkshop(workshop1);
      entry2.setWorkshop(workshop2);
      entry3.setWorkshop(workshop3);
      
      MaintenanceSchedule schedule1 = new MaintenanceSchedule(LocalDate.of(2024, 7, 1), "Regular maintenance");
      vehicle1.addMaintenanceSchedule(schedule1);
      workshop1.addSchedule(schedule1);
      schedule1.setVehicle(vehicle1);
      schedule1.setWorkshop(workshop1);
      MaintenanceSchedule schedule2 = new MaintenanceSchedule(LocalDate.of(2024, 8, 15), "Engine check-up");
      vehicle2.addMaintenanceSchedule(schedule2);
      workshop2.addSchedule(schedule2);
      schedule2.setVehicle(vehicle2);
      schedule2.setWorkshop(workshop1);
   }
}
