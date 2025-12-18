package se.lu.ics.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import se.lu.ics.models.Vehicle;
import se.lu.ics.models.VehicleType;
import se.lu.ics.models.VehicleRegister;
import se.lu.ics.models.AppModel;
import se.lu.ics.controllers.AppController;

import javafx.event.ActionEvent;

public class VehicleViewController {

    private AppModel appModel;
    private AppController appController;

    @FXML
    private TableView<Vehicle> tableVehicles;
    @FXML
    private TableColumn<Vehicle, Number> columnVin;
    @FXML
    private TableColumn<Vehicle, String> columnName;
    @FXML
    private TableColumn<Vehicle, Number> columnCapacity;
    @FXML
    private TableColumn<Vehicle, String> columnLocation;
    @FXML
    private TableColumn<Vehicle, Boolean> columnAvailable;
    @FXML
    private TableColumn<Vehicle, VehicleType> columnType;
    @FXML
    private Button buttonVehicleAdd;
    @FXML
    private Button buttonVehicleRemove;
    @FXML
    private Button buttonVehicleEdit;
    @FXML
    private Button buttonVehicleSearch;
    @FXML
    private TextField textFieldVehicleSearch;
    @FXML
    private Label labelResponse;
    @FXML
    private Button buttonViewMaintenanceSchedule;
    @FXML
    private Button buttonViewServiceHistory;
    @FXML
    private Button buttonBack;

    @FXML
    public void initialize() {
        columnVin.setCellValueFactory(new PropertyValueFactory<>("vin"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        columnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        columnAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));

        updateTable();
    }

    @FXML
    public void handleButtonVehicleAddAction(ActionEvent event) {
        appController.showInputVehicleView();
    }

    @FXML
    public void handleButtonVehicleRemoveAction(ActionEvent event) {
    }

    @FXML
    public void handleButtonVehicleEditAction(ActionEvent event) {
    }

    @FXML
    public void handleTextFieldVehicleSearchAction(ActionEvent event) {
    }

    @FXML
    public void handleButtonVehicleSearchAction(ActionEvent event) {
    }

    @FXML
    public void handleButtonViewMaintenanceScheduleAction(ActionEvent event) {
        appController.showMaintenenceViewVehicle();
    }

    @FXML
    public void handleButtonViewServiceHistoryAction(ActionEvent event) {
        Vehicle selectedVehicle = tableVehicles.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            appController.showServiceHistoryEntryViewVehicle(selectedVehicle);
        } else {          
            labelResponse.setText("Please select a vehicle to view its service history.");
        }
    }

    @FXML
    public void handleButtonBackAction(ActionEvent event) {
        appController.showMainView();
    }

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
        updateTable();
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
    private void updateTable() {
    if (appModel != null && tableVehicles != null) {
        tableVehicles.setItems(appModel.getVehicleRegister().getVehicles());
    }
}

}
