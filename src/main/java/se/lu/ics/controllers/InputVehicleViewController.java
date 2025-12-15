package se.lu.ics.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import se.lu.ics.models.AppModel;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.VehicleType;

public class InputVehicleViewController {

    private AppModel appModel;
    private AppController appController;

    @FXML
    private TextField textFieldLocation;
    @FXML
    private TextField textFieldCapacity;
    @FXML
    private TextField textFieldType;
    @FXML
    private TextField textFieldName;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @FXML
    private void handleButtonApplyAction(ActionEvent event) {
    }

    @FXML
    private void handleTextFieldLocationAction(ActionEvent e) {
    }

    @FXML
    private void handleTextFieldCapacityAction(ActionEvent e) {
    }

    @FXML
    private void handleTextFieldTypeAction(ActionEvent e) {
    }

    @FXML
    private void handleTextFieldNameAction(ActionEvent e) {
    }
}
