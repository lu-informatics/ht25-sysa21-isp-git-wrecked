package se.lu.ics.models;
import java.util.ArrayList;
import java.time.LocalDate;

public class ServiceHistoryEntry {
    private String problemDescription;
    private double cost;
    private ArrayList<String> partsReplaced; 
    private LocalDate serviceDate;
    private ArrayList<String> serviceActivities;
    private Workshop workshop;

    public ServiceHistoryEntry(String problemDescription, double cost, LocalDate date){
        this.problemDescription = problemDescription;
        this.cost = cost;
        this.serviceDate = date;
        this.partsReplaced = new ArrayList<String>();
        this.serviceActivities = new ArrayList<String>();
    }
    public void addServiceActivity(String activity){
        this.serviceActivities.add(activity);  
    }
    public void removeServiceActivity(String activity){
        this.serviceActivities.remove(activity);  
    }
    public ArrayList<String> getServiceActivities(){
        return serviceActivities;  
    }
    public String getProblemDescription(){
        return problemDescription;
    }   
    public void setProblemDescription(String problemDescription){
        this.problemDescription = problemDescription;
    }
    public double getCost(){
        return cost;
    }   
    public void setCost(double cost){
        this.cost = cost;
    }     
    public LocalDate getDate(){
        return serviceDate; 
    }       
    public void setDate(LocalDate date){
        this.serviceDate = date; 
    }  
   public void addPartReplaced(String part){
        this.partsReplaced.add(part);  
    } 
    public void removePartReplaced(String part){
        this.partsReplaced.remove(part);  
    }
    public int getPartsReplaced(){
        return partsReplaced.size();  
    } 
    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }
    public Workshop getWorkshop() {
        return workshop;
    }
}
