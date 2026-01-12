package se.lu.ics.controllers;

import se.lu.ics.models.AppModel;
import se.lu.ics.models.MaintenanceSchedule;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.Workshop;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
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
    private Workshop workshop;
   

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        comboBoxWorkshop.setItems(appModel.getWorkshopRegister().getWorkshops());
    }

    public void setMaintenanceSchedule(MaintenanceSchedule schedule) {
        this.maintenanceSchedule = schedule;
        if (schedule != null) {
            comboBoxWorkshop.setValue(schedule.getWorkshop());
            datePicker.setValue(schedule.getDate());
            textFieldDescription.setText(schedule.getDescription());
        }
    }

    @FXML 
    private ComboBox <Workshop> comboBoxWorkshop;
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
    LocalDate date = datePicker.getValue();
    String description = textFieldDescription.getText().trim();
    Workshop workshop = comboBoxWorkshop.getValue();

    if (comboBoxWorkshop.getValue() == null) {
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
        Workshop foundWorkshop = appModel.getWorkshopRegister().findWorkshop(comboBoxWorkshop.getValue().getName());

        if (foundWorkshop == null) {
            labelResponse.setText("Workshop not found.");
            return;
        }
        MaintenanceSchedule schedule;

        if (maintenanceSchedule == null) {
            schedule = new MaintenanceSchedule(date, description);
            
            
            

        } else {
            schedule = maintenanceSchedule;
            schedule.setDate(date);
            schedule.setDescription(description);
        }   
            schedule.setVehicle(vehicle);
            schedule.setWorkshop(foundWorkshop);
            
            appController.showMaintenenceViewVehicle(vehicle);

            Stage stage = (Stage) buttonApply.getScene().getWindow();
            stage.close();

    } catch (NumberFormatException e) {
        labelResponse.setText("Invalid input format.");
    } catch (IllegalArgumentException e) {
        labelResponse.setText(e.getMessage());
    } catch (IllegalStateException e) {
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
