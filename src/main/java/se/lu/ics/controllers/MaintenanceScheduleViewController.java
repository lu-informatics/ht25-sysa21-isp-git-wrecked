package se.lu.ics.controllers;

import se.lu.ics.models.AppModel;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import se.lu.ics.App;
import se.lu.ics.models.AppModel;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.MaintenanceSchedule;
import se.lu.ics.models.Workshop;
import se.lu.ics.models.WorkshopRegister;
import se.lu.ics.models.VehicleRegister;
import java.time.LocalDate;

public class MaintenanceScheduleViewController {
    
    private AppModel appModel;
    private AppController appController;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
    @FXML
    private Button buttonBack;

    @FXML
    private MenuItem menuItemFindMaintenanceSchedule;

    @FXML
    private MenuItem menuItemBookMaintenance;

    @FXML
    private MenuItem menuItemCancelMaintenance;

    @FXML 
    private TextField searchVin;

    

    @FXML
    private void handleMenuItemFindMaintenanceScheduleAction() {
        
    }

    @FXML
    private void handleMenuItemBookMaintenanceAction() {
        appController.showInputMaintenanceScheduleView();
    }

    @FXML
    private void handleMenuItemCancelMaintenanceAction() {
        
    }

    @FXML
    private void handleButtonBackAction() {
        appController.showWorkshopView();
    }

}
