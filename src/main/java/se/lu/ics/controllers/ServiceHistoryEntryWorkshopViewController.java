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
        tableViewServiceHistory.refresh();
        labelCost.setText(String.valueOf(workshop.getTotalServiceCost()));
    }

    @FXML
    private Label workshopLabel;
    @FXML
    private Label labelCost;
    @FXML
    private TableView<ServiceHistoryEntry> tableViewServiceHistory;
    @FXML
    private TableColumn<ServiceHistoryEntry, String> columnVehicle;
    @FXML
    private TableColumn<ServiceHistoryEntry, LocalDate> columnDate;
    @FXML
    private TableColumn<ServiceHistoryEntry, String> columnProblemDescription;
    @FXML
    private TableColumn<ServiceHistoryEntry, Double> columnCost;
    @FXML
    private TableColumn<ServiceHistoryEntry, Integer> columnPartsReplaced;
    @FXML
    private TableColumn<ServiceHistoryEntry, String> tableColumnServiceActivity;
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
        columnVehicle.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnProblemDescription.setCellValueFactory(new PropertyValueFactory<>("problemDescription"));
        columnCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        columnPartsReplaced.setCellValueFactory(new PropertyValueFactory<>("partsReplaced"));
        tableColumnServiceActivity.setCellValueFactory(new PropertyValueFactory<>("serviceActivities"));

        tableViewServiceHistory.refresh();
    }

    @FXML
    public void handleMenuItemAddServiceHistoryAction(ActionEvent event) {

        appController.showInputServiceHistoryEntryWorkshopView(workshop);
        tableViewServiceHistory.refresh();
        labelCost.setText(String.valueOf(workshop.getTotalServiceCost()));
    }

    @FXML
    public void handleMenuItemRemoveServiceHistoryAction(ActionEvent event) {
        ServiceHistoryEntry selectedEntry = tableViewServiceHistory.getSelectionModel().getSelectedItem();
        if (selectedEntry == null) {
            labelErrorMessage.setText("Please select a service history entry to remove.");
            return;
        }
        try {
            workshop.removeService(selectedEntry);
            selectedEntry.getVehicle().removeServiceHistoryEntry(selectedEntry);
            labelCost.setText(String.valueOf(workshop.getTotalServiceCost()));
            tableViewServiceHistory.refresh();
        } catch (IllegalArgumentException e) {
            labelErrorMessage.setText(e.getMessage());
            return;
        }

    }

    @FXML
    public void handleMenuItemEditAction(ActionEvent event) {
        ServiceHistoryEntry selectedEntry = tableViewServiceHistory.getSelectionModel().getSelectedItem();
        if (selectedEntry == null) {
            labelErrorMessage.setText("Please select a service history entry to edit.");
            return;
        }
        appController.showInputEditServiceHistoryEntryWorkshopView(workshop, selectedEntry);
        tableViewServiceHistory.refresh();
        labelCost.setText(String.valueOf(workshop.getTotalServiceCost()));
    }

    @FXML
    public void handleButtonBackAction() {
        appController.showWorkshopView();

    }

}
