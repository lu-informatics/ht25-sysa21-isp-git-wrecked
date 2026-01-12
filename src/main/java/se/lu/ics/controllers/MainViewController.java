package se.lu.ics.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import se.lu.ics.models.AppModel;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.ServiceHistoryEntry;
import se.lu.ics.models.Workshop;

public class MainViewController {
    private AppModel appModel;
    private AppController appController;

    @FXML
    private TextField vinTextField;
    @FXML
    private Button buttonVehicles;
    @FXML
    private Button buttonWorkshops;
    @FXML
    private Label labelMaintenanceJob;
    @FXML
    private Label labelVehicle;
    @FXML
    private Label labelWorkshop;

    // button labels textfields osv. alla knappar för metoderna def.

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
        updateMostExpensiveWorkshop();
        updateAverageMaintenanceCost();
        updateMostExpensiveMaintenanceJob();
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @FXML
    public void handleButtonVehiclesAction(ActionEvent event) {
        appController.showVehicleView();
    }

    @FXML
    public void handleButtonWorkshopsAction(ActionEvent event) {
        appController.showWorkshopView();
    }

    @FXML
    private Label labelMostExpensiveWorkshop;

    private void updateMostExpensiveWorkshop() {
        Workshop w = appModel.getWorkshopRegister().getMostExpensiveWorkshop();

        if (w != null) {
            labelMostExpensiveWorkshop.setText(w.getName());
        } else {
            labelMostExpensiveWorkshop.setText("No workshops with service history.");
        }
    }

    @FXML
    private Label labelAverageMaintenanceCost;

    private void updateAverageMaintenanceCost() {
        double avarageCost = appModel.getVehicleRegister().calculateAvarageCost();
        labelAverageMaintenanceCost.setText(String.format("%.2f kr", avarageCost));

    }

    private void updateMostExpensiveMaintenanceJob() {
        ServiceHistoryEntry mostExpensiveJob = appModel.getVehicleRegister().getMostExpensiveService();

        if (mostExpensiveJob == null) {
            labelMaintenanceJob.setText("No maintenance jobs available.");
            labelVehicle.setText("");
            labelWorkshop.setText("");
            return;
        }
        Vehicle vehicleForMost = appModel.getVehicleRegister().getVehicleForServiceEntry(mostExpensiveJob);

        labelMaintenanceJob.setText("Most expensive job: " + mostExpensiveJob.getProblemDescription());
        labelVehicle.setText("Vehicle: " + vehicleForMost.getName() + "  " + vehicleForMost.getVin());
        labelWorkshop.setText("Workshop: "
                + (mostExpensiveJob.getWorkshop() != null ? mostExpensiveJob.getWorkshop().getName() : "Unknown"));
    }
}