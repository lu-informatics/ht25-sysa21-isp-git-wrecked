package se.lu.ics.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory; //för att koppla getter metoder till tabell kolumner
import java.time.LocalDate;

import se.lu.ics.models.AppModel;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.VehicleType;
import se.lu.ics.models.ServiceHistoryEntry;

public class ServiceHistoryEntryVehicleViewController {
    private AppModel appModel;
    private AppController appController;
    private Vehicle vehicle;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @FXML
    private Label vehicleLabel;
    @FXML
    private TableView<ServiceHistoryEntry> serviceHistoryTable;
    @FXML
    private TableColumn<ServiceHistoryEntry, LocalDate> dateColumn;
    @FXML
    private TableColumn<ServiceHistoryEntry, String> problemDescriptionColumn;
    @FXML
    private TableColumn<ServiceHistoryEntry, Double> costColumn;
    @FXML
    private TableColumn<ServiceHistoryEntry, Integer> partsReplacedColumn; // ska det vara integer iställer för int?
    @FXML
    private TableColumn<ServiceHistoryEntry, String> workshopColumn;
    @FXML
    private TableColumn<ServiceHistoryEntry, String> serviceActivitiesColumn;
    @FXML
    private TextField textFieldSearchServiceHistory;
    @FXML
    private MenuItem menuItemAddServiceHistory;
    @FXML
    private MenuItem menuItemRemoveServiceHistory;
    @FXML
    private Button buttonBack;

    @FXML
    private void handleTextFieldSearchServiceHistoryAction() {
    }

    @FXML
    private void initialize() { // kan göra detta under med lambdas men vet nt om det är överkurs

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        problemDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("problemDescription"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        partsReplacedColumn.setCellValueFactory(new PropertyValueFactory<>("partsReplaced"));
        workshopColumn.setCellValueFactory(new PropertyValueFactory<>("workshop"));
        serviceActivitiesColumn.setCellValueFactory(new PropertyValueFactory<>("serviceActivities"));
    }

    public void initData(Vehicle vehicle) {
        this.vehicle = vehicle;

        vehicleLabel.setText(vehicle.getName() + "" + vehicle.getVin());
        serviceHistoryTable.getItems().setAll(vehicle.getServiceHistory());
    }

    @FXML
    private void handleMenuItemAddServiceHistoryAction() {
        appController.showInputServiceHistoryEntryView(vehicle);
    }

    @FXML
    private void handleMenuItemRemoveServiceHistoryAction() {
        ServiceHistoryEntry selectedEntry = serviceHistoryTable.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            vehicle.removeServiceHistoryEntry(selectedEntry);
            serviceHistoryTable.getItems().setAll(vehicle.getServiceHistory());
        } else {
            System.out.println("Please select a service history entry to remove.");
        }
    }

    @FXML
    private void handleButtonBackAction() {
        appController.showVehicleView();
    }
}