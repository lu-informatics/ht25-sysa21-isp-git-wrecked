package se.lu.ics.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.security.Provider.Service;
import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import se.lu.ics.models.AppModel;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.VehicleType;
import se.lu.ics.models.ServiceHistoryEntry;
import se.lu.ics.models.AppModel;

public class ServiceHistoryEntryView {

    private AppModel appModel;
    private AppController appController;


    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
    @FXML
    private Label labelErrorMessage;
    @FXML
    private TextField textFieldSearch;
    @FXML
    private MenuItem menuItemAddServiceHistory;
    @FXML
    private MenuItem menuItemRemoveServiceHistory;
    @FXML
    private MenuItem menuItemEditServiceHistory;
    @FXML
    private Button buttonBack;
    @FXML
    private TableView<ServiceHistoryEntry> tableViewServiceHistory;
    @FXML
    private TableColumn<ServiceHistoryEntry, LocalDate> columnDate;
    @FXML
    private TableColumn<ServiceHistoryEntry, String> columnProblemDescription;
    @FXML
    private TableColumn<ServiceHistoryEntry, Double> columnCost;
    @FXML
    private TableColumn<ServiceHistoryEntry, Integer> columnPartsReplaced;

    @FXML
    private void handleTextFieldSearchAction() {
        
    }

    @FXML
    private void handleMenuItemAddServiceHistoryAction() {
        appController.showInputServiceHistoryEntryView();
    }

    @FXML
    private void handleMenuItemRemoveServiceHistoryAction() {
        ServiceHistoryEntry selectedEntry = tableViewServiceHistory.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            tableViewServiceHistory.getItems().remove(selectedEntry);
        } else {
            System.out.println("Please select a service history entry to remove.");
        }
    }

    @FXML
    private void handleMenuItemEditServiceHistoryAction() {
        ServiceHistoryEntry selectedEntry = tableViewServiceHistory.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            appController.showEditInputServiceHistoryEntryView(selectedEntry); }
        else {
            labelErrorMessage.setText("Please select a service history entry to edit.");
    

        
            
        }
    }

    @FXML
    private void handleButtonBackAction() {
        appController.showWorkshopView();
    }
    @FXML
    private void initialize() {
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnProblemDescription.setCellValueFactory(new PropertyValueFactory<>("problemDescription"));
        columnCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        columnPartsReplaced.setCellValueFactory(new PropertyValueFactory<>("partsReplaced"));
    }
}
