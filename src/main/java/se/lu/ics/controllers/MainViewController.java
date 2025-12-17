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

}
