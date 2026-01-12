package se.lu.ics.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory; //för att koppla getter metoder till tabell kolumner
import javafx.event.ActionEvent;

import java.time.LocalDate;
import se.lu.ics.models.AppModel;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.ServiceHistoryEntry;
import se.lu.ics.models.Workshop;

public class ServiceHistoryEntryViewVehicleController {
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
        vehicleLabel.setText(vehicle.getName() + " " + vehicle.getVin());
        serviceHistoryTable.setItems(vehicle.getServiceHistory());
        labelCost.setText(String.format("%.2f", vehicle.getVehicleTotalServiceCost()));
        serviceHistoryTable.refresh();
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
    private MenuItem menuItemEdit;
    @FXML
    private Button buttonBack;
    @FXML
    private Label labelErrorMessage;
    @FXML
    private Label labelCost;
    @FXML
    private void initialize() { // kan göra detta under med lambdas men vet nt om det är överkurs
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        problemDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("problemDescription"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        partsReplacedColumn.setCellValueFactory(new PropertyValueFactory<>("partsReplaced"));
        workshopColumn.setCellValueFactory(new PropertyValueFactory<>("workshop"));
        serviceActivitiesColumn.setCellValueFactory(new PropertyValueFactory<>("serviceActivities"));
        serviceHistoryTable.refresh();
        }

    @FXML
    public void handleMenuItemAddServiceHistoryAction() {
         if(vehicle==null){
            labelErrorMessage.setText("Vehicle not set.");
            return;
        }

        if (vehicle.getPartsReplaced() >= 100) {
            labelErrorMessage.setText("Cannot add service history entry. Vehicle marked as unavailable due to excessive parts replaced.");
            return;
        }
        if (vehicle.getTotalCost() >= 100000) {
            labelErrorMessage.setText("Cannot add service history entry. Vehicle marked as unavailable due to excessive total cost.");
            return;
        }
       
        appController.showInputServiceHistoryEntryViewVehicle(vehicle);
        serviceHistoryTable.setItems(vehicle.getServiceHistory());
        serviceHistoryTable.refresh();
        labelCost.setText(String.valueOf(vehicle.getTotalCost()));
    }

    @FXML
    public void handleMenuItemEditAction(ActionEvent event) {
        ServiceHistoryEntry selectedEntry = serviceHistoryTable.getSelectionModel().getSelectedItem();
        if (selectedEntry == null) {
            labelErrorMessage.setText("Please select a service history entry to edit.");
            return;
        }
        appController.showInputEditServiceHistoryEntryViewVehicle(vehicle, selectedEntry);
        serviceHistoryTable.setItems(vehicle.getServiceHistory());
        serviceHistoryTable.refresh();
        labelCost.setText(String.valueOf(vehicle.getTotalCost()));
    }

    @FXML
    public void handleMenuItemRemoveServiceHistoryAction(ActionEvent event) {
        ServiceHistoryEntry selectedEntry = serviceHistoryTable.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            try {
                vehicle.removeServiceHistoryEntry(selectedEntry);
                selectedEntry.getWorkshop().removeService(selectedEntry);
                labelCost.setText(String.valueOf(vehicle.getTotalCost()));
            } catch (IllegalArgumentException e) {
                labelErrorMessage.setText(e.getMessage());
                return;
            }
        } 
        serviceHistoryTable.refresh();
    }

    @FXML
    public void handleButtonBackAction() {
        appController.showVehicleView();

    }

    
    }
