package se.lu.ics.models;

import java.util.ArrayList;

public class WorkshopRegister {
    private ArrayList<Workshop> workshops;

    public WorkshopRegister() {
            this.workshops = new ArrayList<>();    
    }

    public void setWorkshops(ArrayList<Workshop> workshops) {
        this.workshops = workshops;
    }
    public ArrayList<Workshop> getWorkshops() {
        return workshops;
    }

    public void addWorkshop(Workshop workshop) {
        this.workshops.add(workshop);
    }

    public Workshop findWorkshopByName(String name) {
        for (Workshop workshop : workshops) {
            if (workshop.getName().equals(name)) {
                return workshop;
            }
        }
        return null; // Return null if no workshop with the given name is found
    }

    public void removeWorkshop(Workshop workshop) {
        this.workshops.remove(workshop);
    }

    public Workshop getMostExpensiveWorkshop() {
        Workshop mostExpensive = null;
        double highestCost = 0.0;
        for (Workshop workshop : workshops) {
            double totalCost = 0.0;
            ArrayList<ServiceHistoryEntry> services = workshop.getServices();
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
