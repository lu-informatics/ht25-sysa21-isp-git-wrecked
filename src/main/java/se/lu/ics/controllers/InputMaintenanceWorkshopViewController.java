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
    private MaintenanceSchedule maintenanceSchedule;
    private Vehicle vehicle;

     public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

     public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public void setMaintenanceSchedule(MaintenanceSchedule selectedSchedule) {
        this.maintenanceSchedule = selectedSchedule;

        if (maintenanceSchedule != null) {
            textFieldVehicle.setText(String.valueOf(maintenanceSchedule.getVin()));
            textFieldDescription.setText(maintenanceSchedule.getDescription());
            datePicker.setValue(maintenanceSchedule.getDate());
        }
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

        // Validate all fields
        if (vehicleVinStr.isEmpty()) {
            labelResponse.setText("Vehicle VIN cannot be empty.");
            return;
        }

        if (description.isEmpty()) {
            labelResponse.setText("Maintenance description cannot be empty.");
            return;
        }

        if (date == null) {
            labelResponse.setText("Maintenance date must be selected.");
            return;
        }

        try {
            int vehicleVin = Integer.parseInt(vehicleVinStr);
            Vehicle foundVehicle = appModel.getVehicleRegister().findVehicle(vehicleVin);

            if (foundVehicle == null) {
                labelResponse.setText("Vehicle with VIN " + vehicleVin + " not found.");
                return;
            }
            MaintenanceSchedule schedule;
        
            if (maintenanceSchedule == null) {
                schedule = new MaintenanceSchedule(date, description);
                
                labelResponse.setText("Maintenance schedule added successfully.");
            } else {
                schedule = maintenanceSchedule;
                schedule.setDate(date);
                schedule.setDescription(description);
                schedule.setVehicle(foundVehicle);
                labelResponse.setText("Maintenance schedule updated successfully.");
            }   
                schedule.setWorkshop(workshop);               
                schedule.setVehicle(foundVehicle);

            appController.showWorkshopSchedulesView(workshop);
            Stage stage = (Stage) buttonApply.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            labelResponse.setText("Invalid VIN format. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            labelResponse.setText("Error: " + e.getMessage());
        } catch (Exception e) {
            labelResponse.setText("An unexpected error occurred: " + e.getMessage());
        }
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

