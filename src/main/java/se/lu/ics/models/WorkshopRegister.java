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

        for (Workshop tempWorkshop : workshops) {
            if (tempWorkshop.getName().equals(workshop.getName())) {
                throw new IllegalArgumentException("Workshop with this name already exists.");
            }
        }
        this.workshops.add(workshop);
    }

    public Workshop findWorkshop(String name) {
        for (Workshop workshop : workshops) {
            if (workshop.getName().equals(name)) {
                return workshop;
            } else {
                throw new IllegalArgumentException("No workshop with this name exists.");
            }
        }
        throw new IllegalArgumentException("No workshop with this name exists.");
        }

    public void removeWorkshop(String name) { //BOrde dessa verkligen ha String name som parameter?
        for (int i = 0; i < workshops.size(); i++) {
            Workshop w = workshops.get(i);

            if (w.getName().equalsIgnoreCase(name)) {
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
