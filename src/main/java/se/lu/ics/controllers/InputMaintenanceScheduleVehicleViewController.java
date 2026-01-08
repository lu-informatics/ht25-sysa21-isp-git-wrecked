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
    private MaintenanceSchedule maintenanceSchedule;
   

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setMaintenanceSchedule(MaintenanceSchedule schedule) {
        this.maintenanceSchedule = schedule;
        if (schedule != null) {
            textFieldWorkshop.setText(schedule.getWorkshop().getName());
            datePicker.setValue(schedule.getDate());
            textFieldDescription.setText(schedule.getDescription());
        }
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
    String workshopName = textFieldWorkshop.getText().trim();
    LocalDate date = datePicker.getValue();
    String description = textFieldDescription.getText().trim();

    if (workshopName.isEmpty()) {
        labelResponse.setText("Workshop cannot be empty.");
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
        Workshop foundWorkshop = appModel.getWorkshopRegister().findWorkshop(workshopName);

        if (foundWorkshop == null) {
            labelResponse.setText("Workshop not found.");
            return;
        }

        if (maintenanceSchedule != null) {

            Vehicle oldVehicle = maintenanceSchedule.getVehicle();
            Workshop oldWorkshop = maintenanceSchedule.getWorkshop();

            maintenanceSchedule.setDate(date);
            maintenanceSchedule.setDescription(description);

            
            if (oldVehicle != vehicle) {
                oldVehicle.removeMaintenanceSchedule();
                vehicle.addMaintenanceSchedule(maintenanceSchedule);
            }
            if (oldWorkshop != foundWorkshop) {
                oldWorkshop.removeSchedule(maintenanceSchedule);
                foundWorkshop.addSchedule(maintenanceSchedule);
            }

            labelResponse.setText("Maintenance schedule updated successfully.");

        }
        
        else {
            MaintenanceSchedule newSchedule =
                    new MaintenanceSchedule(date, description);

            vehicle.addMaintenanceSchedule(newSchedule);
            foundWorkshop.addSchedule(newSchedule);

            labelResponse.setText("Maintenance schedule created successfully.");
        }

        appController.showMaintenenceViewVehicle(vehicle);

        Stage stage = (Stage) buttonApply.getScene().getWindow();
        stage.close();

    } catch (NumberFormatException e) {
        labelResponse.setText("Invalid input format.");
    } catch (IllegalArgumentException e) {
        labelResponse.setText(e.getMessage());
    }
}

    
    @FXML
        public void handleButtonBackAction(ActionEvent event) {
            appController.showMaintenenceViewVehicle(vehicle);
            Stage stage=(Stage) buttonBack.getScene().getWindow();
            stage.close();
        }
}
