package se.lu.ics.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

import se.lu.ics.models.AppModel;
import se.lu.ics.models.Workshop;
import se.lu.ics.models.ServiceHistoryEntry;
import se.lu.ics.models.Vehicle;

public class ServiceHistoryEntryWorkshopViewController {
    private AppModel appModel;
    private AppController appController;
    private Workshop workshop;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }      
    public Workshop getWorkshop() {
        return workshop;
    }

    @FXML
    private Label workshopLabel;
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
    private TextField textFieldSearch;
    @FXML
    private MenuItem menuItemAddServiceHistory;
    @FXML
    private MenuItem menuItemRemoveServiceHistory;
    @FXML
    private Button buttonBack;
    @FXML
    private Label labelErrorMessage;

    @FXML
    private void initialize() {
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnProblemDescription.setCellValueFactory(new PropertyValueFactory<>("problemDescription"));
        columnCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        columnPartsReplaced.setCellValueFactory(new PropertyValueFactory<>("partsReplaced"));
    }
     public void initData(Workshop workshop) {
        this.workshop = workshop;

        workshopLabel.setText(workshop.getName());
        tableViewServiceHistory.getItems().setAll(workshop.getServices());
    }
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
    private void handleButtonBackAction() {
        appController.showWorkshopView();
    
    }
}
