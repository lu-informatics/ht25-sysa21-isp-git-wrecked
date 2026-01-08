package se.lu.ics.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkshopRegister {
    private ObservableList<Workshop> workshops;

    public WorkshopRegister() {
        this.workshops = FXCollections.observableArrayList();
    }

    public ObservableList<Workshop> getWorkshops() {
        return FXCollections.unmodifiableObservableList(workshops);
    }

    public void addWorkshop(Workshop workshop) {

        for (Workshop tempWorkshop : workshops) {
            if (tempWorkshop.getName().equals(workshop.getName())) {
                throw new IllegalArgumentException("Workshop with this name already exists.");
            }
        }
        this.workshops.add(workshop);
    }

    public Workshop findWorkshop(String name) {
        if(name==null){
            throw new IllegalArgumentException("No workshop with this name exists.");
        }

        String inputName = name.trim();

        for (Workshop tempWorkshop : workshops) {
            String workshopN=tempWorkshop.getName();

            if (workshopN != null && workshopN.equalsIgnoreCase(inputName)) {
                return tempWorkshop;
            } 
        }
        throw new IllegalArgumentException("No workshop with this name exists.");
    }

    public void removeWorkshop(Workshop workshop) { 
        for (int i = 0; i < workshops.size(); i++) {
            Workshop w = workshops.get(i);

            if (w.getName().equalsIgnoreCase(workshop.getName())) {
                workshops.remove(i);
                return;
            }
        }
        
        throw new IllegalArgumentException("No workshop with this name exists. Try again!");
    }
    // bäst med i loop för att ta bort objekt inne i en loop, ger exception

    public Workshop getMostExpensiveWorkshop() {
        Workshop mostExpensive = null;
        double highestCost = 0.0;

        for (Workshop workshop : workshops) {
            double totalCost = 0.0;
            ObservableList<ServiceHistoryEntry> services = workshop.getServices();
            if (services != null) {
                for (ServiceHistoryEntry service : services) {
                    totalCost += service.getCost();
                }
                if (totalCost > highestCost) {
                    highestCost = totalCost;
                    mostExpensive = workshop;
                }
            }
        }
        return mostExpensive;
    }
}
