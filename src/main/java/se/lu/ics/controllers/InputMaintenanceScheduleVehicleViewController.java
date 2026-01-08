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
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class InputMaintenanceScheduleVehicleViewController {

    private AppModel appModel;
    private AppController appController;
    private Vehicle vehicle;
   

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @FXML
    private TextField textFieldWorkshop;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField textFieldDescription;
    @FXML
    private Button buttonApply;
    @FXML
    private Button buttonBack;
    @FXML
    private Label labelResponse;
    
    @FXML
    public void handleButtonApplyAction(ActionEvent event) {
        String workshop = textFieldWorkshop.getText().trim();
        LocalDate date = datePicker.getValue();
        String description = textFieldDescription.getText().trim();

        if(workshop.isEmpty() || date==null || description.isEmpty()) {
            labelResponse.setText("Please fill in all fields: workshop, date, and description.");
            return;
        }

        Workshop foundWorkshop;

        try{
            foundWorkshop=appModel.getWorkshopRegister().findWorkshop(workshop);
        }catch(IllegalArgumentException e){
            labelResponse.setText(e.getMessage());
            return;
        }
        
        MaintenanceSchedule newSchedule = new MaintenanceSchedule(date, description);

        vehicle.addMaintenanceSchedule(newSchedule);
        newSchedule.setVehicle(vehicle);
        newSchedule.setWorkshop(foundWorkshop);
        foundWorkshop.addSchedule(newSchedule);
        
        appController.showMaintenenceViewVehicle(vehicle);
        Stage stage=(Stage) buttonApply.getScene().getWindow();
        stage.close();
        //stänga fönstret efter apply button
    }
    
    @FXML
        public void handleButtonBackAction(ActionEvent event) {
            appController.showMaintenenceViewVehicle(vehicle);
            Stage stage=(Stage) buttonBack.getScene().getWindow();
            stage.close();
        }
}
