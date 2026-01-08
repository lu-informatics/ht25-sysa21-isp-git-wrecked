package se.lu.ics.controllers;

import se.lu.ics.models.AppModel;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import se.lu.ics.models.MaintenanceSchedule;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.Workshop;
import java.time.LocalDate;

    public class MaintenanceScheduleViewControllerWorkshop {

    private AppModel appModel;
    private AppController appController;
    private Workshop workshop; 

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
        updateTableView();
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @FXML
    private Label labelResponse;
    @FXML
    private Button buttonBack;
    @FXML
    private MenuItem menuItemAdd;
    @FXML
    private MenuItem menuItemEdit;
    @FXML
    private MenuItem menuItemCancel;
    @FXML
    private TableView <MaintenanceSchedule> tableMaintenanceSchedules;
    @FXML
    private TableColumn<MaintenanceSchedule, Number> tableColumnVehicle;
    @FXML
    private TableColumn<MaintenanceSchedule, LocalDate> tableColumnDate;
    @FXML
    private TableColumn<MaintenanceSchedule, String> tableColumnDescription;
    @FXML
    private Label labelWorkshop;

    //koppla tidigare fönster vehicle till detta. 
    public void setWorkshop(Workshop workshop ) {
        this.workshop= workshop;
        labelWorkshop.setText(workshop.getName());
        updateTableView();
    }
    @FXML
    public void initialize(){
        
        tableColumnVehicle.setCellValueFactory(new PropertyValueFactory<>("vin"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        updateTableView();
    }

    private void updateTableView(){
        if (workshop != null && tableMaintenanceSchedules != null) {
            tableMaintenanceSchedules.setItems(workshop.getSchedules());
        }
    }

    @FXML
    public void handleMenuItemAddMaintenanceAction(ActionEvent event) {
        appController.showInputMaintenanceWorkshopView(workshop, null);
        updateTableView();
    }

    @FXML
    public void handleMenuItemEditAction(ActionEvent event) {
        MaintenanceSchedule selectedSchedule = tableMaintenanceSchedules.getSelectionModel().getSelectedItem();
        if (selectedSchedule == null) {
            labelResponse.setText("Please select a maintenance schedule to edit.");
            return;
        }
        appController.showInputMaintenanceWorkshopView(workshop, selectedSchedule);
        updateTableView();
    }
    
    @FXML
    public void handleMenuItemCancelMaintenanceAction(ActionEvent event) {

        MaintenanceSchedule schedule = tableMaintenanceSchedules.getSelectionModel().getSelectedItem();
        if(schedule == null) {
            labelResponse.setText("Please select a maintenance schedule to cancel.");
            return;
        }

        try {
                workshop.removeSchedule(schedule);
            } catch (IllegalArgumentException e) {
                labelResponse.setText(e.getMessage());
            }
        updateTableView();
    }

    @FXML
    public void handleButtonBackAction(ActionEvent event) {
        appController.showWorkshopView();
    }
}
