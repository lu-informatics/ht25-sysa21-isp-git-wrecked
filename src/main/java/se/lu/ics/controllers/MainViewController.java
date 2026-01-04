package se.lu.ics.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import se.lu.ics.models.AppModel;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.VehicleRegister;
import se.lu.ics.models.VehicleType;
import se.lu.ics.models.ServiceHistoryEntry;
import se.lu.ics.models.MaintenanceSchedule;
import se.lu.ics.models.Workshop;
import se.lu.ics.models.WorkshopRegister;

public class MainViewController {
    private AppModel appModel;
    private AppController appController;

    @FXML
    private TextField vinTextField;

    @FXML
    private Button buttonVehicles;

    @FXML
    private Button buttonWorkshops;

    // button labels textfields osv. alla knappar för metoderna def.

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
        updateMostExpensiveWorkshop();
        updateAverageMaintenanceCost();
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @FXML
    private void handleButtonVehiclesAction(ActionEvent event) {
        appController.showVehicleView();
    }

    @FXML
    private void handleButtonWorkshopsAction(ActionEvent event) {
        appController.showWorkshopView();
    }
    @FXML
    private TextField textFieldMostExpensiveWorkshop;

    private void updateMostExpensiveWorkshop() {
        String mostExpensiveWorkshop = "";
        double highestTotalCost = 0.0;

        for (Workshop w : appModel.getWorkshopRegister().getWorkshops()) {
            double totalCostForWorkshop = 0.0;
        
            for (Vehicle v : appModel.getVehicleRegister().getVehicles()) {
        
                for (ServiceHistoryEntry e : w.getServices()) {
                    if (e.getWorkshop() !=null && e.getWorkshop().equals(w)){
                totalCostForWorkshop += e.getCost();
                    }
                }
            }
            
            if (totalCostForWorkshop > highestTotalCost) {
                highestTotalCost = totalCostForWorkshop ;
                mostExpensiveWorkshop = w.getName() + " - Total Cost: " + highestTotalCost;
            }
        }
        if(!mostExpensiveWorkshop.isEmpty()){
        textFieldMostExpensiveWorkshop.setText(mostExpensiveWorkshop + "(" + highestTotalCost + " SEK)");
        } else {
            textFieldMostExpensiveWorkshop.setText("No workshops with service history.");
        }
    }
    @FXML
    private TextField textFieldAverageMaintenanceCost;

    private void updateAverageMaintenanceCost() {
        double totalCost = 0.0;
        int serviceCount = 0;

        for (Vehicle v:appModel.getVehicleRegister().getVehicles()) {
            for (ServiceHistoryEntry e : v.getServiceHistory()) {
                totalCost += e.getCost();
                serviceCount++;
            }
        }
        if (serviceCount > 0) {
            double averageCost = totalCost / serviceCount;
            textFieldAverageMaintenanceCost.setText(String.format("%.2f SEK", averageCost));
        } else {
            textFieldAverageMaintenanceCost.setText("0.00");
        }
    }
}