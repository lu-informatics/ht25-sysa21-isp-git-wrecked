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

public class InputMaintenanceWorkshopViewController {
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

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField textFieldDescription;
    @FXML
    private TextField textFieldVehicle;
    @FXML
    private Button buttonApply;
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonReset;
    @FXML
    private Label labelResponse;

    @FXML
    public void handleButtonApplyAction(ActionEvent event) {
        String vehicleVinStr = textFieldVehicle.getText().trim();
        String description = textFieldDescription.getText().trim();
        LocalDate date = datePicker.getValue();

        if(vehicleVinStr.isEmpty() || description.isEmpty() || date==null) {
            labelResponse.setText("Please fill in all fields: vehicle(VIN), description, and date.");
            return;
        }

        int vehicleVin=Integer.parseInt(vehicleVinStr);
        Vehicle foundVehicle;

        try{
            foundVehicle=appModel.getVehicleRegister().findVehicle(vehicleVin);
        }catch(Exception e){
            labelResponse.setText(e.getMessage());
            return;
        }

        MaintenanceSchedule newSchedule = new MaintenanceSchedule(date, description);

        foundVehicle.addMaintenanceSchedule(newSchedule);
        newSchedule.setVehicle(foundVehicle);
        newSchedule.setWorkshop(workshop);
        workshop.getSchedules().add(newSchedule);

        appController.showWorkshopSchedulesView(workshop);
        Stage stage=(Stage) buttonApply.getScene().getWindow();
        stage.close();
        
    }

     @FXML
    public void handleButtonBackAction(ActionEvent event) {
            appController.showWorkshopSchedulesView(workshop);
            Stage stage=(Stage) buttonBack.getScene().getWindow();
            stage.close();
        }
    @FXML
    public void handleButtonResetAction(ActionEvent event) {
        datePicker.setValue(null);
        textFieldDescription.clear();
        textFieldVehicle.clear();
        labelResponse.setText("All fields have been cleared.");
    }

}

