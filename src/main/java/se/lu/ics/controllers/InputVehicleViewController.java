package se.lu.ics.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import se.lu.ics.models.AppModel;
import se.lu.ics.models.MaintenanceSchedule;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.VehicleType;
import javafx.stage.Stage;

public class InputVehicleViewController {

    private AppModel appModel;
    private AppController appController;
    private Vehicle vehicle;

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
    @FXML
    private RadioButton radioButtonAvailable;
    @FXML
    private RadioButton radioButtonNotAvailable;
    @FXML
    private ToggleGroup availabilityGroup;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;

        if (vehicle != null) {
            textFieldName.setText(vehicle.getName());
            comboBoxVehicleType.setValue(vehicle.getType());
            textFieldCapacity.setText(String.valueOf(vehicle.getCapacity()));
            textFieldLocation.setText(vehicle.getLocation());

            // NYTT — sätt rätt radioknapp
            if (vehicle.isAvailable()) {
                radioButtonAvailable.setSelected(true);
            } else {
                radioButtonNotAvailable.setSelected(true);
            }
        }
    }

    @FXML
    public void initialize() {
        comboBoxVehicleType.getItems().setAll(VehicleType.values());
        radioButtonAvailable.setSelected(true);

    }

    @FXML
    public void handleButtonApplyAction(ActionEvent event) {

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
            labelResponse.setText("Capacity must be a number.");
            return;
        }
        try {
            if (vehicle == null) {

                boolean available = radioButtonAvailable.isSelected();
                Vehicle newVehicle = new Vehicle(location, capacity, available, type, name);
                appModel.getVehicleRegister().addVehicle(newVehicle);
            } else {

                vehicle.setName(name);
                vehicle.setType(type);
                vehicle.setCapacity(capacity);
                vehicle.setLocation(location);
                vehicle.setAvailable(radioButtonAvailable.isSelected());

            }

            appController.showVehicleView();
            Stage stage = (Stage) buttonApply.getScene().getWindow();
            stage.close();
        } catch (IllegalArgumentException e) {
            labelResponse.setText(e.getMessage());
            return;
        }
        catch (IllegalStateException e) {
            labelResponse.setText(e.getMessage());
            return;
        }
    }

    @FXML
    public void handleButtonResetAction(ActionEvent event) {
        textFieldName.clear();
        textFieldCapacity.clear();
        textFieldLocation.clear();
    }

    @FXML
    public void handleButtonBackAction(ActionEvent event) {
        appController.showVehicleView();
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        stage.close();
    }

}
