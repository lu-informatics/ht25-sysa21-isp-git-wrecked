package se.lu.ics.models;

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

      Vehicle vehicle1 = new Vehicle("Location 1", 1000, true, VehicleType.MEDIUM_TRUCK, "Volvo");
      Vehicle vehicle2 = new Vehicle("Location 2", 2000, false, VehicleType.LARGE_TRUCK, "Scania");
      Vehicle vehicle3 = new Vehicle("Location 3", 500, true, VehicleType.VAN, "Ford");
      Vehicle vehicle4 = new Vehicle("Location 4", 750, true, VehicleType.LARGE_TRUCK, "Mercedes");
      Vehicle vehicle5 = new Vehicle("Location 5", 1500, false, VehicleType.MEDIUM_TRUCK, "SAAB");

      Workshop workshop1 = new Workshop("Workshop 1", true,  "Address 1");
      Workshop workshop2 = new Workshop("Workshop 2", false, "Address 2");
      Workshop workshop3 = new Workshop("Workshop 3", true,  "Address 3");
      Workshop workshop4 = new Workshop("Workshop 4", true,  "Address 4");
      // Add test data to vehicle register
      vehicleRegister.addVehicle(vehicle1);
      vehicleRegister.addVehicle(vehicle2);
      vehicleRegister.addVehicle(vehicle3);
      vehicleRegister.addVehicle(vehicle4);
      vehicleRegister.addVehicle(vehicle5);
      // Add test data to workshop register
      workshopRegister.addWorkshop(workshop1);
   }
}
