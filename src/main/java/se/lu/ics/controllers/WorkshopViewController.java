package se.lu.ics.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
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
import se.lu.ics.models.WorkshopRegister;
import se.lu.ics.models.AppModel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

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
    private MenuItem menuItemEditWorkshop;

    @FXML
    private TextField textSearch;

    @FXML
    private Button buttonViewEntries;

    @FXML
    private Button buttonViewSchedules;

    @FXML
    private Button backToMainMenuButton;

    @FXML
    private void handleMenuItemAddWorkshopAction(ActionEvent event) {
        appController.showInputWorkshopView();

    }

    @FXML
    private void handleMenuItemRemoveWorkshopAction(ActionEvent event) {
        Workshop selectedWorkshop = workshopTable.getSelectionModel().getSelectedItem();
        if (selectedWorkshop != null) {
            workshopTable.getItems().remove(selectedWorkshop);
            appModel.getWorkshopRegister().removeWorkshop(selectedWorkshop.getName());
        } else {
            labelErrorMessage.setText("Please select a workshop to remove.");
        }

    }


    @FXML
    private void handleButtonBackToMainMenuAction(ActionEvent event) {
        appController.showMainView();
    }

    @FXML
    private void handleTextSearchAction(ActionEvent event) {
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
    private void handleButtonViewEntriesAction(ActionEvent event) {
        Workshop selectedWorkshop = workshopTable.getSelectionModel().getSelectedItem();
        if (selectedWorkshop != null) {
        appController.showWorkshopEntriesView(selectedWorkshop);
        } else {
            labelErrorMessage.setText("Please select a workshop to view its entries.");
        }



    }

    @FXML
    private void initialize() {
        tableColumnAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableColumnServiceEntries.setCellValueFactory(new PropertyValueFactory<>("serviceEntriesCount"));

        updateTable();
    }

    @FXML
    private void handleTableViewWorkshopsAction() {

    }

    @FXML
    private void handleButtonViewSchedulesAction(ActionEvent event) {
        Workshop selectedWorkshop = workshopTable.getSelectionModel().getSelectedItem();
        if (selectedWorkshop != null) {
            appController.showWorkshopSchedulesView(selectedWorkshop);
        } else {
            labelErrorMessage.setText("Please select a workshop to view its schedules.");
        }

    }
    private void updateTable() {
        if (appModel != null && workshopTable != null) {
            workshopTable.setItems(appModel.getWorkshopRegister().getWorkshops());
        }
    }
    

}
