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

      Vehicle vehicle1 = new Vehicle("Malmö", 1200, VehicleType.MEDIUM_TRUCK, "Volvo");
      Vehicle vehicle2 = new Vehicle("Göteborg", 2200, VehicleType.LARGE_TRUCK, "Scania");
      Vehicle vehicle3 = new Vehicle("Lund", 700, VehicleType.VAN, "Ford");
      Vehicle vehicle4 = new Vehicle("Helsingborg", 2000, VehicleType.LARGE_TRUCK, "Mercedes");
      Vehicle vehicle5 = new Vehicle("Uppsala", 1500, VehicleType.MEDIUM_TRUCK, "SAAB");
      Vehicle vehicle6 = new Vehicle("Västerås", 800, VehicleType.VAN, "Toyota");
      Vehicle vehicle7 = new Vehicle("Örebro", 1300, VehicleType.MEDIUM_TRUCK, "Nissan");
      Vehicle vehicle8 = new Vehicle("Stockholm", 850, VehicleType.VAN, "Mercedes Sprinter");
      Vehicle vehicle9 = new Vehicle("Jönköping", 1400, VehicleType.MEDIUM_TRUCK, "Scania P280");
      Vehicle vehicle10 = new Vehicle("Linköping", 2300, VehicleType.LARGE_TRUCK, "Volvo FH16");
      Vehicle vehicle11 = new Vehicle("Karlstad", 1600, VehicleType.MEDIUM_TRUCK, "MAN TGM");
      Vehicle vehicle12 = new Vehicle("Kalmar", 600, VehicleType.VAN, "Iveco Daily");
      Vehicle vehicle13 = new Vehicle("Borås", 2100, VehicleType.LARGE_TRUCK, "Scania R500");
      Vehicle vehicle14 = new Vehicle("Falun", 900, VehicleType.VAN, "Ford Transit");
      Vehicle vehicle15 = new Vehicle("Sundsvall", 1500, VehicleType.MEDIUM_TRUCK, "DAF LF");
      Vehicle vehicle16 = new Vehicle("Gävle", 1700, VehicleType.MEDIUM_TRUCK, "Renault D");
      Vehicle vehicle17 = new Vehicle("Luleå", 2400, VehicleType.LARGE_TRUCK, "Volvo FMX");

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
      vehicleRegister.addVehicle(vehicle11);
      vehicleRegister.addVehicle(vehicle12);
      vehicleRegister.addVehicle(vehicle13);
      vehicleRegister.addVehicle(vehicle14);
      vehicleRegister.addVehicle(vehicle15);
      vehicleRegister.addVehicle(vehicle16);
      vehicleRegister.addVehicle(vehicle17);

      workshopRegister.addWorkshop(workshop1);
      workshopRegister.addWorkshop(workshop2);
      workshopRegister.addWorkshop(workshop3);
      workshopRegister.addWorkshop(workshop4);
   }
}
