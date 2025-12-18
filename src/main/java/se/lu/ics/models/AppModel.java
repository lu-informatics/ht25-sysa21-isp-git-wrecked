package se.lu.ics.models;

public class AppModel {
   private VehicleRegister vehicleRegister;
   private ServiceHistoryEntry serviceHistoryEntry;
   private WorkshopRegister workshopRegister;

   public AppModel() {
      this.vehicleRegister = new VehicleRegister();
      this.serviceHistoryEntry = new ServiceHistoryEntry();
      this.workshopRegister = new WorkshopRegister();

      addTestData();
   }

   public VehicleRegister getVehicleRegister() {
      return vehicleRegister;
   }

   public ServiceHistoryEntry getServiceHistoryEntry() {
      return serviceHistoryEntry;
   }

   public WorkshopRegister getWorkshopRegister() {
      return workshopRegister;
   }

   private void addTestData() {

      Vehicle vehicle1 = new Vehicle("Location 1", 1000,VehicleType.MEDIUM_TRUCK, "Volvo");
      Workshop workshop1 = new Workshop("Workshop 1", true,  "Address 1");
      // Add test data to vehicle register
      vehicleRegister.addVehicle(new Vehicle("Location 1", 1000,VehicleType.MEDIUM_TRUCK, "Volvo"));
      // Add test data to workshop register
      workshopRegister.addWorkshop(new Workshop("Workshop 1", true,  "Address 1"));
   }
}
