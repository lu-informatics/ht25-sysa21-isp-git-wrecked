package se.lu.ics.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.lu.ics.models.Workshop;
import se.lu.ics.models.AppModel;


public class WorkshopViewController {
    private AppModel appModel;
    private AppController appController;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
        workshopTable.setItems(appModel.getWorkshopRegister().getWorkshops());
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @FXML
    private TableView<Workshop> workshopTable;

    @FXML
    private Label labelErrorMessage;

    @FXML
    private TableColumn<Workshop, String> tableViewWorkshops;

    @FXML
    private TableColumn<Workshop, String> tableColumnName;

    @FXML
    private TableColumn<Workshop, String> tableColumnAddress;

    @FXML
    private TableColumn<Workshop, Boolean> tableColumnType;

    @FXML
    private TableColumn<Workshop, Integer> tableColumnServiceEntries;

    @FXML
    private MenuItem menuItemAddWorkshop;

    @FXML
    private MenuItem menuItemRemoveWorkshop;

    @FXML
    private MenuItem menuItemEdit;

    @FXML
    private TextField textSearch;

    @FXML
    private Button buttonViewEntries;

    @FXML
    private Button buttonViewSchedules;

    @FXML
    private Button backToMainMenuButton;

    @FXML
    public void handleMenuItemAddWorkshopAction(ActionEvent event) {
        appController.showInputWorkshopView();

    }

     @FXML
    public void handleMenuItemRemoveWorkshopAction(ActionEvent event) {
        Workshop selectedWorkshop = workshopTable.getSelectionModel().getSelectedItem();
        if (selectedWorkshop != null) {
            try {
                appModel.getWorkshopRegister().removeWorkshop(selectedWorkshop);
                updateTable();
            } catch (IllegalArgumentException e) {
                labelErrorMessage.setText(e.getMessage());
            }
        } else {
            labelErrorMessage.setText("Please select a workshop to remove.");
        }
    }

    @FXML
    public void handleMenuItemEditAction(ActionEvent event) {
        Workshop editWorkshop = workshopTable.getSelectionModel().getSelectedItem();

        if (editWorkshop != null) {
            appController.showEditInputWorkshopView(editWorkshop);
        } else {
            labelErrorMessage.setText("Please select a workshop to edit.");
        }
    }

    @FXML
    public void handleButtonBackToMainMenuAction(ActionEvent event) {
        appController.showMainView();
    }

    @FXML
    public void handleTextSearchAction(ActionEvent event) {
        String searchText = textSearch.getText().toLowerCase();
        ObservableList<Workshop> filteredWorkshops = FXCollections.observableArrayList();

        for (Workshop workshop : appModel.getWorkshopRegister().getWorkshops()) {
            if (workshop.getName().toLowerCase().contains(searchText) ||
                    workshop.getAddress().toLowerCase().contains(searchText) ||
                    workshop.getType().toLowerCase().contains(searchText)) {
                filteredWorkshops.add(workshop);
            }
        }
        workshopTable.setItems(filteredWorkshops);
    }

    @FXML
    public void handleButtonViewServiceHistoryAction(ActionEvent event) {
        Workshop selectedWorkshop = workshopTable.getSelectionModel().getSelectedItem();
        if (selectedWorkshop != null) {
        appController.showServiceHistoryEntryWorkshopView(selectedWorkshop);
        } else {
            labelErrorMessage.setText("Please select a workshop to view its Service History");
        }
    }

    @FXML
    private void initialize() {
        tableColumnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableColumnServiceEntries.setCellValueFactory(new PropertyValueFactory<>("serviceCount"));

        
        workshopTable.refresh();
    }

    @FXML
    public void handleButtonViewSchedulesAction(ActionEvent event) {
        Workshop selectedWorkshop = workshopTable.getSelectionModel().getSelectedItem();
        if (selectedWorkshop != null) {
            appController.showMaintenanceScheduleWorkshopView(selectedWorkshop);
        } else {
            labelErrorMessage.setText("Please select a workshop to view its Maintenance Schedules");
        }

    }
    private void updateTable() {
        if (appModel != null && workshopTable != null) {
            workshopTable.setItems(appModel.getWorkshopRegister().getWorkshops());
        }
    }
}
