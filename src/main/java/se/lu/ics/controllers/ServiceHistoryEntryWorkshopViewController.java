package se.lu.ics.controllers;

import javafx.event.ActionEvent;
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


public class ServiceHistoryEntryWorkshopViewController {
    private AppModel appModel;
    private AppController appController;
    private Workshop workshop;
    private ServiceHistoryEntry serviceHistoryEntry;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
        workshopLabel.setText(workshop.getName());
        tableViewServiceHistory.setItems(workshop.getServices());
    }      
    public void setServiceHistoryEntry(ServiceHistoryEntry serviceHistoryEntry) {
       this.serviceHistoryEntry = serviceHistoryEntry;
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
    private MenuItem menuItemEdit;
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
    public void handleMenuItemAddServiceHistoryAction(ActionEvent event) {
       ServiceHistoryEntry selectedEntry = tableViewServiceHistory.getSelectionModel().getSelectedItem();
    if (workshop == null) {
        labelErrorMessage.setText("Pleasse selcet a workshop to add a service history entry.");
        return;
    }
        appController.showInputServiceHistoryEntryViewWorkshop(workshop, new ServiceHistoryEntry());
    }

   @FXML
    private void handleMenuItemRemoveServiceHistoryAction() {
    ServiceHistoryEntry selectedEntry = tableViewServiceHistory.getSelectionModel().getSelectedItem();
    if (selectedEntry == null) {
        labelErrorMessage.setText("Please select a service history entry to remove.");
        return;
    }
    workshop.removeService(selectedEntry); 
}


    @FXML
    private void handleButtonBackAction() {
        appController.showWorkshopView();
    
    }
    
}
