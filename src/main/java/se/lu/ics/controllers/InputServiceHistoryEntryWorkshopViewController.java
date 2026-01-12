package se.lu.ics.controllers;

import se.lu.ics.models.AppModel;
import se.lu.ics.models.ServiceHistoryEntry;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.Workshop;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class InputServiceHistoryEntryWorkshopViewController {

    private AppModel appModel;
    private AppController appController;
    private Workshop workshop;
    private ServiceHistoryEntry serviceHistoryEntry;



    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;

        comboBoxVehicle.setItems(appModel.getVehicleRegister().getVehicles());
    }

    public void setEntry(ServiceHistoryEntry serviceHistoryEntry) {
        this.serviceHistoryEntry = serviceHistoryEntry;

        if (serviceHistoryEntry != null) {
            datePickerServiceDate.setValue(serviceHistoryEntry.getDate());
            textFieldProblemDescription.setText(serviceHistoryEntry.getProblemDescription());
            textFieldCost.setText(String.valueOf(serviceHistoryEntry.getCost()));
            textFieldPartsReplaced.setText(String.valueOf(serviceHistoryEntry.getPartsReplaced()));
            comboBoxVehicle.setValue(serviceHistoryEntry.getVehicle());
            textFieldServiceActivities.setText(String.join(", ", serviceHistoryEntry.getServiceActivities()));
            
        }
    }

    

    @FXML
    private DatePicker datePickerServiceDate;

    @FXML
    private TextField textFieldProblemDescription;

    @FXML
    private TextField textFieldCost;

    @FXML
    private TextField textFieldPartsReplaced;

    @FXML
    private ComboBox<Vehicle> comboBoxVehicle;

    @FXML
    private Button buttonApply;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonReset;

    @FXML
    private Label labelResponse;
    @FXML
    private TextField textFieldServiceActivities;

    
    @FXML
    public void handleButtonResetAction() {
        datePickerServiceDate.setValue(null);
        textFieldProblemDescription.clear();
        textFieldCost.clear();
        textFieldPartsReplaced.clear();
        comboBoxVehicle.setValue(null);    
        textFieldServiceActivities.clear();
    }
    
    @FXML
    public void handleButtonApplyAction() {

    LocalDate date = datePickerServiceDate.getValue();
    String problem = textFieldProblemDescription.getText().trim();
    String costText = textFieldCost.getText().trim();
    String partsText = textFieldPartsReplaced.getText().trim();
    Vehicle vehicle = comboBoxVehicle.getValue();
    String activitiesText = textFieldServiceActivities.getText().trim();

    

    // Validate all fields
    if (date == null) {
        labelResponse.setText("Service date must be selected.");
        return;
    }

    if (problem.isEmpty()) {
        labelResponse.setText("Problem description cannot be empty.");
        return;
    }

    if (costText.isEmpty()) {
        labelResponse.setText("Cost cannot be empty.");
        return;
    }

    if (partsText.isEmpty()) {
        labelResponse.setText("Parts replaced value cannot be empty.");
        return;
    }

    if (vehicle == null) {
        labelResponse.setText("A vehicle must be selected.");
        return;
    }
    if (activitiesText.isEmpty() || !activitiesText.matches("[a-öA-Ö, ]+")) {
        labelResponse.setText("Service activities must be filled with words.");
        return;
    }


    double cost;
    int parts;

    try {
        cost = Double.parseDouble(costText);
        if (cost < 0) {
            labelResponse.setText("Cost cannot be negative.");
            return;
        }
    } catch (NumberFormatException e) {
        labelResponse.setText("Cost must be a valid number.");
        return;
    }

    try {
        parts = Integer.parseInt(partsText);
        if (parts < 0) {
            labelResponse.setText("Parts replaced cannot be negative.");
            return;
        }
    } catch (NumberFormatException e) {
        labelResponse.setText("Parts replaced must be a whole number.");
        return;
    }

    try {
        ServiceHistoryEntry entry;

        if (serviceHistoryEntry == null) {
            entry = new ServiceHistoryEntry(problem, cost, date, parts);   
            
        }


         else { 
            entry = serviceHistoryEntry;  
            
            entry.setDate(date);
            entry.setProblemDescription(problem);
            entry.setCost(cost);
            entry.setPartsReplaced(parts);
            entry.setVehicle(vehicle);
            
        }
            entry.addServiceActivity(partsText);
            entry.setWorkshop(workshop);
            entry.setVehicle(vehicle);
;       labelResponse.setText("Service history entry saved successfully.");
        Stage stage = (Stage) buttonApply.getScene().getWindow();
        stage.close();
        
        

    } catch (IllegalArgumentException e) {
        labelResponse.setText("Error: " + e.getMessage());
    } catch (Exception e) {
        labelResponse.setText("An unexpected error occurred: " + e.getMessage());
    }
}


    @FXML
    public void handleButtonBackAction() {
        appController.showServiceHistoryEntryWorkshopView(workshop);
        ((Stage) buttonBack.getScene().getWindow()).close();
    }

    

    

}


