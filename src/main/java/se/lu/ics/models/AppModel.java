package se.lu.ics.models;

public class AppModel {
 private VechicleRegister vechicleRegister;
 private ServiceHistoryEntry serviceHistoryEntry;
 private WorkshopRegister workshopRegister;

 public ApppModel(){
    this.vechicleRegister = new VechicleRegister();
    this.serviceHistoryEntry = new ServiceHistoryEntry();
    this.workshopRegister = new WorkshopRegister();
 }
public VechicleRegister getVechicleRegister(){
    return vechicleRegister;
 } 
 public ServiceHistoryEntry getServiceHistoryEntry(){
    return serviceHistoryEntry;
 } 
 public WorkshopRegister getWorkshopRegister(){
    return workshopRegister;
 }
}
