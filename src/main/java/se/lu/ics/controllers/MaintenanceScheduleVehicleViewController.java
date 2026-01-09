package se.lu.ics.controllers;

import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import se.lu.ics.models.AppModel;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.MaintenanceSchedule;
import se.lu.ics.models.Workshop;
import se.lu.ics.models.WorkshopRegister;
import java.time.LocalDate;


public class MaintenanceScheduleVehicleViewController {
    private AppModel appModel;
    private AppController appController;
    private Vehicle vehicle; //varför ha? för hämta värdet av vehicle med get?

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @FXML
    private MenuItem menuItemBook;
    @FXML
    private MenuItem menuItemCancel;
    @FXML
    private MenuItem menuItemEdit;
    @FXML
    private Label labelShowDate;
    @FXML
    private Label labelShowWorkshop;
    @FXML
    private Label labelShowMaintenance;
    @FXML
    private Label labelVehicleVin;
    @FXML
    private Label labelShowMostExpensiveWorkshop;
    @FXML
    private Label labelShowMostExpensiveMaintenance;
    @FXML
    private Label labelResponse;
    @FXML
    private Button buttonBack;
   
   
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        labelVehicleVin.setText(vehicle.getName() +" " + vehicle.getVin());
        loadMaintenanceSchedule();
        updateMostExpensiveWorkshop();
    }

    private void loadMaintenanceSchedule(){
        int vin= vehicle.getVin();

        Vehicle vehicle = appModel.getVehicleRegister().findVehicle(vin);
        MaintenanceSchedule schedule = vehicle.getMaintenanceSchedule();

        if (schedule != null) {
            LocalDate date = schedule.getDate();
            
            labelShowDate.setText(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // för localdate
            labelShowMaintenance.setText(schedule.getDescription());
            labelShowWorkshop.setText(schedule.getWorkshop().getName());
            labelResponse.setText("Maintenance schedule found.");
            }else {
                labelResponse.setText("No maintenance schedule found.");
            }
        }

    @FXML
    public void handleMenuItemBookMaintenanceAction(ActionEvent event) {
         if(vehicle==null){
            labelResponse.setText("Vehicle not set.");
            return;
        }

        if (vehicle.getMaintenanceSchedule() != null) {
            labelResponse.setText("The vehicle already has a maintenance schedule. You can only have one.");
            return;
        }
       
        appController.showInputMaintenanceScheduleVehicleView(vehicle);
       
        loadMaintenanceSchedule();
        updateMostExpensiveWorkshop();
    }

    @FXML
    public void handleMenuItemEditMaintenanceAction(ActionEvent event) {
        if (vehicle == null) {
            labelResponse.setText("Vehicle not set.");
            return;
        }
        MaintenanceSchedule schedule = vehicle.getMaintenanceSchedule();
        if (schedule == null) {
            labelResponse.setText("No maintenance schedule to edit.");
            return;
        }
        appController.showInputEditMaintenanceScheduleVehicle(vehicle, schedule);
        loadMaintenanceSchedule();
        updateMostExpensiveWorkshop();
    }


    @FXML
    public void handleMenuItemCancelMaintenanceAction(ActionEvent event) {
    int vin=vehicle.getVin();

            Vehicle vehicle = appModel.getVehicleRegister().findVehicle(vin);
            MaintenanceSchedule schedule = vehicle.getMaintenanceSchedule();

            if (schedule != null) {
                LocalDate date = schedule.getDate();
                if (date == null) {
                    labelShowDate.setText("No date.");
                    labelResponse.setText("No maintenance schedule to cancel.");
            } else {
            vehicle.removeMaintenanceSchedule(schedule);
            schedule.getWorkshop().removeSchedule(schedule);
            labelResponse.setText("Maintenance schedule cancelled.");
            updateMostExpensiveWorkshop();
            clearMaintenanceLabels();
        }
    }
}

    // metod för att visa most expensive uppdaterad.

    private void updateMostExpensiveWorkshop() {
        WorkshopRegister workshopRegister = appModel.getWorkshopRegister();
        Workshop mostExpensiveWorkshop = workshopRegister.getMostExpensiveWorkshop();

        if (mostExpensiveWorkshop != null) {
            labelShowMostExpensiveWorkshop.setText(mostExpensiveWorkshop.getName());

        } else {
            labelShowMostExpensiveWorkshop.setText("No data availible.");
        }
    }

    private void clearMaintenanceLabels() {
        labelShowDate.setText("");
        labelShowMaintenance.setText("");
        labelShowWorkshop.setText("");
    }

    @FXML
    public void handleButtonBackAction() {
        appController.showVehicleView();
    }
}

