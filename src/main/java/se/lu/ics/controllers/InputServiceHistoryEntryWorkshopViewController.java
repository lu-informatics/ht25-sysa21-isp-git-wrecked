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
import javafx.scene.control.Alert;

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
    public void handleButtonResetAction() {
        datePickerServiceDate.setValue(null);
        textFieldProblemDescription.clear();
        textFieldCost.clear();
        textFieldPartsReplaced.clear();
        comboBoxVehicle.setValue(null);
    }
    @FXML
    public void handleButtonApplyAction() {

    LocalDate date = datePickerServiceDate.getValue();
    String problem = textFieldProblemDescription.getText().trim();
    String costText = textFieldCost.getText().trim();
    String partsText = textFieldPartsReplaced.getText().trim();
    Vehicle vehicle = comboBoxVehicle.getValue();
    

    // Validate all fields
    if (date == null) {
        showError("Service date must be selected.");
        return;
    }

    if (problem.isEmpty()) {
        showError("Problem description cannot be empty.");
        return;
    }

    if (costText.isEmpty()) {
        showError("Cost cannot be empty.");
        return;
    }

    if (partsText.isEmpty()) {
        showError("Parts replaced value cannot be empty.");
        return;
    }

    if (vehicle == null) {
        showError("A vehicle must be selected.");
        return;
    }

    double cost;
    int parts;

    try {
        cost = Double.parseDouble(costText);
        if (cost < 0) {
            showError("Cost cannot be negative.");
            return;
        }
    } catch (NumberFormatException e) {
        showError("Cost must be a valid number.");
        return;
    }

    try {
        parts = Integer.parseInt(partsText);
        if (parts < 0) {
            showError("Parts replaced cannot be negative.");
            return;
        }
    } catch (NumberFormatException e) {
        showError("Parts replaced must be a whole number.");
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

        showError("Service history entry saved successfully!");
        Stage stage = (Stage) buttonApply.getScene().getWindow();
        stage.close();
        
        

    } catch (IllegalArgumentException e) {
        showError("Error: " + e.getMessage());
    } catch (Exception e) {
        showError("An unexpected error occurred: " + e.getMessage());
    }
}


    @FXML
    public void handleButtonBackAction() {
        appController.showWorkshopEntriesView(workshop);
        ((Stage) buttonBack.getScene().getWindow()).close();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    

}


