package se.lu.ics.models;

public class AppModel {
   private VehicleRegister vehicleRegister;
   private ServiceHistoryEntry serviceHistoryEntry;
   private WorkshopRegister workshopRegister;

   public AppModel() {
      this.vehicleRegister = new VehicleRegister();
      this.serviceHistoryEntry = new ServiceHistoryEntry();
      this.workshopRegister = new WorkshopRegister();
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
}
