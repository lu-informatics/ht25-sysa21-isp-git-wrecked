package se.lu.ics.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import se.lu.ics.models.AppModel;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.VehicleType;

public class InputVehicleViewController {

    private AppModel appModel;
    private AppController appController;

    @FXML
    private TextField textFieldName;
    @FXML
    private ComboBox<VehicleType> comboBoxVehicleType;
    @FXML
    private TextField textFieldCapacity;
    @FXML
    private TextField textFieldLocation;
    @FXML
    private Button buttonApply;
    @FXML
    private Button buttonReset;
    @FXML
    private Button buttonBack;
    @FXML
    private Label labelResponse;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @FXML
    private void handleTextFieldNameAction(ActionEvent e) {
    }

    @FXML
    private void handleComboBoxVehicleTypeAction(ActionEvent e) {
    }

    @FXML
    public void initialize() {
        comboBoxVehicleType.getItems().setAll(VehicleType.values());
    }

    @FXML
    private void handleTextFieldCapacityAction(ActionEvent e) {
    }

    @FXML
    private void handleTextFieldLocationAction(ActionEvent e) {
    }

    @FXML
    private void handleButtonApplyAction(ActionEvent event) {
        String name = textFieldName.getText();
        VehicleType type = comboBoxVehicleType.getValue();
        String capacityStr = textFieldCapacity.getText();
        String location = textFieldLocation.getText();

        if (name.isEmpty() || type == null || capacityStr.isEmpty() || location.isEmpty()) {
            labelResponse.setText("Please fill in all fields.");
            return;
        }

        int capacity;
        try {
            capacity = Integer.parseInt(capacityStr);
        } catch (NumberFormatException e) {
            labelResponse.setText("Capacity must be a valid number.");
            return;
        }

        Vehicle newVehicle = new Vehicle(location, capacity, type, name);
        appModel.getVehicleRegister().addVehicle(newVehicle);
        appController.showVehicleView();
    }

    @FXML
    private void handleButtonResetAction(ActionEvent event) {
        textFieldName.clear();
        textFieldCapacity.clear();
        textFieldLocation.clear();
    }

    @FXML
    private void handleButtonBackAction(ActionEvent event) {
        appController.showVehicleView();
    }

}
