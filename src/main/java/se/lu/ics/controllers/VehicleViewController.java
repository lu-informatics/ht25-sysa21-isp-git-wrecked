package se.lu.ics.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import se.lu.ics.models.Vehicle;
import se.lu.ics.models.VehicleType;
import se.lu.ics.models.AppModel;

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
    private TableColumn<Vehicle, String> columnAvailable;
    @FXML
    private TableColumn<Vehicle, VehicleType> columnType;
    @FXML
    private MenuButton menuButtonOptions;
    @FXML
    private MenuItem menuItemEdit;
    @FXML
    private MenuItem menuItemAddVehicle;
    @FXML
    private MenuItem menuItemRemoveVehicle;
    @FXML
    private TextField textFieldVehicleSearch;
    @FXML
    private Label labelFindVehicle;
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
        columnAvailable.setCellValueFactory(new PropertyValueFactory<>("availableString"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));

        tableVehicles.refresh();
    }

    @FXML
    public void handleMenuItemAddVehicleAction(ActionEvent event) {
        appController.showInputVehicleView();
    }

    @FXML
    public void handleMenuItemEditAction(ActionEvent event) {
        Vehicle selectedVehicle = tableVehicles.getSelectionModel().getSelectedItem();

        if (selectedVehicle != null) {
            appController.showEditInputVehicleView(selectedVehicle);
        } else {
            labelResponse.setText("Please select a vehicle to edit");
        }
        
    }

    @FXML
    public void handleMenuItemRemoveVehicleAction(ActionEvent event) {
        Vehicle selectedVehicle = tableVehicles.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            try {
                appModel.getVehicleRegister().removeVehicle(selectedVehicle.getVin());
                updateTable();
            } catch (IllegalArgumentException e) {
                labelResponse.setText(e.getMessage());
            }
        } else {
            labelResponse.setText("Please select a vehicle to remove");
        }
    }

    @FXML
    public void handleTextFieldVehicleSearchAction(ActionEvent event) {

        String searchText = textFieldVehicleSearch.getText().trim();

        if (searchText.isEmpty()) {
            tableVehicles.getSelectionModel().clearSelection();
            labelResponse.setText("Enter a VIN number to search.");
            return;
        }

        try {
            int vin = Integer.parseInt(searchText);
            Vehicle foundVehicle = appModel.getVehicleRegister().findVehicle(vin);

            tableVehicles.getSelectionModel().select(foundVehicle);
            tableVehicles.scrollTo(foundVehicle);

        } catch (NumberFormatException e) {
            labelResponse.setText("Please enter a valid VIN number");
        } catch (IllegalArgumentException e) {
            labelResponse.setText(e.getMessage());
        }

    }

    @FXML
    public void handleButtonViewMaintenanceScheduleAction(ActionEvent event) {
        Vehicle selectedVehicle = tableVehicles.getSelectionModel().getSelectedItem();

        if (selectedVehicle == null) {
            labelResponse.setText("Please select a vehicle to view its maintenance schedule");
            return;
        }

        appController.showMaintenanceScheduleVehicleView(selectedVehicle);
    }

    @FXML
    public void handleButtonViewServiceHistoryAction(ActionEvent event) {
        Vehicle selectedVehicle = tableVehicles.getSelectionModel().getSelectedItem();

        if (selectedVehicle == null) {
            labelResponse.setText("Please select a vehicle to view its service history");
            return;
        }

        appController.showServiceHistoryEntryVehicleView(selectedVehicle);
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
