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

        if (vehicleVinStr.isEmpty() || description.isEmpty() || date == null) {
            labelResponse.setText("Please fill in all fields: vehicle (VIN), description, and date.");
            return;
        }

        try {
            int vehicleVin = Integer.parseInt(vehicleVinStr);
            Vehicle foundVehicle = appModel.getVehicleRegister().findVehicle(vehicleVin);

            if (maintenanceSchedule != null) {
                
                maintenanceSchedule.setDate(date);
                maintenanceSchedule.setDescription(description);
                maintenanceSchedule.setVehicle(foundVehicle);
                maintenanceSchedule.setWorkshop(workshop);
            } else {
                
                MaintenanceSchedule newSchedule = new MaintenanceSchedule(date, description);
                newSchedule.setVehicle(foundVehicle);
                newSchedule.setWorkshop(workshop);
                foundVehicle.addMaintenanceSchedule(newSchedule);
                workshop.getSchedules().add(newSchedule);
            }

            appController.showWorkshopSchedulesView(workshop);
            Stage stage = (Stage) buttonApply.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            labelResponse.setText("Invalid VIN. Please enter a valid number.");
        } catch (Exception e) {
            labelResponse.setText(e.getMessage());
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

