package se.lu.ics.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import se.lu.ics.models.AppModel;
import se.lu.ics.models.ServiceHistoryEntry;
import se.lu.ics.models.Workshop;
import se.lu.ics.models.Vehicle;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.time.LocalDate;

public class InputServiceHistoryEntryViewController {

    private AppModel appModel;
    private AppController appController;
    private Vehicle vehicle;

    @FXML
    private DatePicker datePickerServiceDate;
    @FXML
    private TextField textFieldProblemDescription;
    @FXML
    private TextField textFieldPartsReplaced;
    @FXML
    private TextField textFieldCost;
    @FXML
    private ComboBox<Workshop> comboBoxWorkshop;
    @FXML
    private TextField textFieldServiceActivites;
    @FXML
    private Button buttonApply;
    @FXML
    private Button buttonBack;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void handleButtonApplyAction(ActionEvent event) {
    }

    @FXML
    private void handleButtonBackAction(ActionEvent event) {
    }

}
