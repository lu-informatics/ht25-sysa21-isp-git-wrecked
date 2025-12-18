package se.lu.ics.controllers;

import se.lu.ics.models.AppModel;
import se.lu.ics.models.MaintenanceSchedule;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.Workshop;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


public class InputMaintenanceScheduleViewController {

    private AppModel appModel;
    private AppController appController;
    private MaintenanceSchedule schedule;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setSchedule(MaintenanceSchedule schedule) {
        this.schedule = schedule;
    }

    @FXML
    private TextField textFieldWorkshop;
    @FXML
    private TextField textFieldDate;
    @FXML
    private TextField textFieldDescription;
    @FXML
    private Button buttonApply;
    @FXML
    private Button buttonBack;
    @FXML
    private Label labelResponse;

    @FXML
    private void handleButtonApplyAction(ActionEvent event) {
        String workshop = textFieldWorkshop.getText();
        String dateT = textFieldDate.getText();
        String description = textFieldDescription.getText();

        if(workshop.isEmpty() || dateT.isEmpty() || description.isEmpty()) {
            labelResponse.setText("Please fill in all fields: workshop, date, and description.");
            return;
        }
            LocalDate date; //deklarera
            
        try {
            LocalDate date = LocalDate.parse(dateT); // gör till typ local date
        }catch(Exception e){
                labelResponse.setText("Invalid date format. Please use YYYY-MM-DD.");
                return;
            }

        MaintenanceSchedule newSchedule = new MaintenanceSchedule(date, description);
        Vehicle vehicle = appModel.getVehicleRegister().findVehicle(vin);

            MaintenanceSchedule schedule = new MaintenanceSchedule(date, description);

            Workshop foundWorkshop = appModel.getWorkshopRegister().findWorkshop(workshop);

            vehicle.addMaintenanceSchedule(schedule);
            schedule.setVehicle(vehicle);
            schedule.setWorkshop(foundWorkshop);
    
        appController.showMaintenenceViewVehicle();
        }

    @FXML
        private void handleButtonBackAction(ActionEvent event) {
            appController.showMaintenenceViewVehicle();
        }

}
